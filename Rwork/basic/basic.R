#R에서 사용하는 주석문
num = 100 # num이 100이다.
num
num <- 1000 # num변수에 1000을 저장하라는 의미
num
num==1000 # num이 1000과 같은지 비교 TRUE, FALSE
num==100
num!=1000
num>100
num<100

test <- "myR"
test 
myR = num
test <- myR

result1 <- 100 # 숫자형
result2 <- "test" #문자형
class(result1)
class(result2)
test <- as.character(result1)
test
class(test)
test <- as.numeric(result1)
test
