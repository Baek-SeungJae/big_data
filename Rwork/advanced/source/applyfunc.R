library(ggplot2)
data(package="MASS")
library("MASS")
data("Boston")
head(Boston)
t(t(colnames((Boston))))
#반복작업을 할 때 사용할 수 있도록 함수를 지원
df <- head(Boston[,1:7])

#반복작업을 할 때 사용할 수 있도록 함수를 지원
#margin=1이면 행방향, 2이면 열방향
df[,"total"] <- apply(X=df , MARGIN =1, FUN="sum")
df[,"avg"] <- apply(X=df , MARGIN =1, FUN="mean")
df

round(apply(X=df,MARGIN=2, FUN="sum"),4)
round(apply(X=df,MARGIN=2, FUN="mean"),4)

#apply의 margin속성을 2로 정의한것과 같다. sapply()
sapply(X=df, FUN="mean")

t(t(sapply(X=df, FUN="class")))
