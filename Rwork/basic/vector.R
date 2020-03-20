# =, <- 같은것처럼 보이지만 <-를 이용해서 변수에 데이터를 할당

a = 100
b <- 100

a
b
sqrt(9)
sqrt(x=9) # x는 함수의 매개변수로 인식 x를 외부에서 사용불가
sqrt(x <- 9)
x
? c
myvector1 <- c(100,200,300)
myvector1
myvector1[1]
myvector1[2]
myvector1[3]
myvector2 <- c(10,20,30)
myvector1 + myvector2

mytext <- c("java", "R", "hadoop", "android")
mytext[1,2] # matrix에 사용하므로 벡터에서 쓸 수 없다.
mytext[c(1,4)] # 벡터의 1, 4번 요소만 출력
mytext[c(1:4)] # 1~4번 요소만 출력
1:100
mytext[-1] # 1번요소 제외한 나머지
mytext[c(-1:-3)]
mytext[-c(1:3)]

mytext[c(T,T,F,F)] #True로 표현된 요소만 가져온다. (True:T, False:F)
mytext[mytext=="R"]
numlist <-1:100
numlist
numlist %%2==0 # %%가 나머지 구하기
numlist[c(numlist %%2==0)] # 짝수구하기

? seq
numlist2 <- seq(log10(1), log10(100), length.out = 10)
numlist2

v1 <- c(70,80,90,100)
names(v1)
names(v1) <- c("국어","수학","영어","자바")
v1
mean(numlist2)