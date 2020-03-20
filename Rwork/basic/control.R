num <- 88
if(num>=90){
  print("합격")
}else{
  print("불합격")
}

myv <- c(1:10)
for(i in myv){
  print(i)
}

# 1부터 10까지 합 구하기

sum=0
myv <- c(1:10)
for(i in myv){
  sum = sum + i
}
print(sum)

sum(myv)
