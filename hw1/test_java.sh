#Compile the code
# if the file HW1 exits then remove it
if [ -f HW1 ]; then
    rm HW1
fi
javac HW1.java
# if the file HW1 does not exit then exit the test
if [ ! -f HW1.class ]; then
    echo   "\033[1;91mCompile FAILED.\033[0m"
    exit
fi
# clean
rm *.out *.stderr *.stdcout
java HW1 "tiny1.txt" "out_tiny1_" 1>tinty1.stdcout 2>tiny1.stderr
bash -c 'diff -iEBwu <(sort out_tiny1_1.txt) <(sort tiny1_1.txt) >tiny1_1.diff'
if [ $? -ne 0 ]; then
    # display "test case 1 FAILED" to the screen. The word "FAILED" is highlighted in Red color
    echo -e "Test case tiny1_1    \033[1;91mFAILED.\033[0m"
else
    # display "test case 1 PASSED" to the screen. The word "PASSED" is highlighted in Green color
    echo -e  "Test case tiny1_1    \033[1;92mPASSED.\033[0m"
    # remove the file 1.diff
    rm -f tiny1_1.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny1_2.txt) <(sort tiny1_2.txt) >tiny1_2.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny1_2    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny1_2    \033[1;92mPASSED.\033[0m"
    rm -f tiny1_2.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny1_3.txt) <(sort tiny1_3.txt) >tiny1_31.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny1_3    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny1_3    \033[1;92mPASSED.\033[0m"
    rm -f tiny1_3.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny1_4.txt) <(sort tiny1_4.txt) >tiny1_4.diff'
if [ $? -ne 0 ]; then
    echo  -e "Test case tiny1_4    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny1_4    \033[1;92mPASSED.\033[0m"
    rm -f tiny1_4.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny1_5.txt) <(sort tiny1_5.txt) >tiny1_5.diff'
if [ $? -ne 0 ]; then
    echo  -e "Test case tiny1_5    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny1_5    \033[1;92mPASSED.\033[0m"
    rm -f tiny1_5.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny1_6.txt) <(sort tiny1_6.txt) >tiny1_6.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny1_6    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny1_6    \033[1;92mPASSED.\033[0m"
    rm -f tiny1_6.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny1_7.txt) <(sort tiny1_7.txt) >tiny1_7.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny1_7    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny1_7    \033[1;92mPASSED.\033[0m"
    rm -f tiny1_7.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny1_8.txt) <(sort tiny1_8.txt) >tiny1_8.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny1_8    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny1_8    \033[1;92mPASSED.\033[0m"
    rm -f tiny1_8.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny1_9.txt) <(sort tiny1_9.txt) >tiny1_9.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny1_9    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny1_9    \033[1;92mPASSED.\033[0m"
    rm -f tiny1_9.diff
fi
java HW1 "tiny2.txt" "out_tiny2_" 1>tiny2.stdcout 2>tiny2.stderr
bash -c 'diff -iEBwu <(sort out_tiny2_1.txt) <(sort tiny2_1.txt) >tiny2_1.diff'
if [ $? -ne 0 ]; then
    # display "test case 1 FAILED" to the screen. The word "FAILED" is highlighted in Red color
    echo -e "Test case tiny2_1    \033[1;91mFAILED.\033[0m"
else
    # display "test case 1 PASSED" to the screen. The word "PASSED" is highlighted in Green color
    echo -e  "Test case tiny2_1    \033[1;92mPASSED.\033[0m"
    # remove the file 1.diff
    rm -f tiny2_1.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny2_2.txt) <(sort tiny2_2.txt) >tiny2_2.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny2_2    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny2_2    \033[1;92mPASSED.\033[0m"
    rm -f tiny2_2.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny2_3.txt) <(sort tiny2_3.txt) >tiny2_31.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny2_3    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny2_3    \033[1;92mPASSED.\033[0m"
    rm -f tiny2_3.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny2_4.txt) <(sort tiny2_4.txt) >tiny2_4.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny2_4    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny2_4    \033[1;92mPASSED.\033[0m"
    rm -f tiny2_4.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny2_5.txt) <(sort tiny2_5.txt) >tiny2_5.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny2_5    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny2_5    \033[1;92mPASSED.\033[0m"
    rm -f tiny2_5.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny2_6.txt) <(sort tiny2_6.txt) >tiny2_6.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny2_6    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny2_6    \033[1;92mPASSED.\033[0m"
    rm -f tiny2_6.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny2_7.txt) <(sort tiny2_7.txt) >tiny2_7.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny2_7    \033[1;91mFAILED.\033[0m"
else
    echo  -e "Test case tiny2_7    \033[1;92mPASSED.\033[0m"
    rm -f tiny2_7.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny2_8.txt) <(sort tiny2_8.txt) >tiny2_8.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny2_8    \033[1;91mFAILED.\033[0m"
else
    echo -e  "Test case tiny2_8    \033[1;92mPASSED.\033[0m"
    rm -f tiny2_8.diff
fi
bash -c 'diff -iEBwu <(sort out_tiny2_9.txt) <(sort tiny2_9.txt) >tiny2_9.diff'
if [ $? -ne 0 ]; then
    echo -e  "Test case tiny2_9    \033[1;91mFAILED.\033[0m"
else
    echo  -e "Test case tiny2_9    \033[1;92mPASSED.\033[0m"
    rm -f tiny2_9.diff
fi
