#matrix

mymat1 <- matrix(1:15, ncol=3) # 3열로 작성
mymat1
mymat1 <- matrix(1:15, ncol=3, byrow = T) #3열로 표현하되 값을 row방향으로 증가
mymat1
mymat1[2:3,c(1:3)]
mymat1[1,] # 1행의 모든 것
mymat1[,1] # 1열의 모든 것
mymat1[c(-1,-3),]
mymat1[c(1,3),]


mymat1[c(2:4),c(2,3)]
colnames(mymat1) <- c("a","b","c")
mymat1
rownames(mymat1) <- c("r1","r2","r3","r4","r5")
mymat1
mean(mymat1[])
