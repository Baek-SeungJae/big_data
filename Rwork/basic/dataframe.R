#dataframe만들기 - 1. matrix를 dataframe으로 변환

dataframeMat1 <- matrix(1:15,ncol=3,byrow=T)
dataframeMat1
dataframeMat1[,1]
dataframeMat2 <- data.frame(dataframeMat1)
dataframeMat2


# 매트릭스는 모든 타입을 한가지 자료형으로 사용해야 하는 문제가 있다.

mydataframe <-data.frame(dataframeMat1)
mydataframe[,1]
mydataframe[,1] <- as.character(mydataframe[,1])
mydataframe[,1]
# str함수는 dataframe 내부의 데이터형을 볼 수 있는 함수
str(mydataframe)
# 1열만 데이터가 다르게 찍혀있는 것을 볼 수 있다.
mydataframe$X1
mydataframe$X2
mydataframe$X3
# $를 이용해서 열의 이름을 접근할 수 있다.

col4 <- c(1,1,1,1,1)
mydataframe$x4 <-col4
mydataframe

#필요한 곳에서 데이터프레임을 매트릭스로 변환
mydataframe  <- as.matrix(mydataframe)
mydataframe


#dataframe만들기 2- 벡터를 여러개 만들어서 dataframe을 작성
eng <- c(100,99,90)
math <- c(100,99,100)
mydf1 <- data.frame(eng,math)
mydf1


m <- matrix(1:15, ncol=3, byrow=T)
m
m <- t(m)
d <- data.frame(m)
d



제품 <- c("사과","딸기","수박")
가격<- c(1800,1500,3000)
판매량 <- c(24,38,13)
market <- data.frame(제품,가격,판매량)
market
colMeans(market[,2:3])
colMeans(market["판매량"])