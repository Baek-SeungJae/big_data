set.seed(1232)
a <- sample(1:10, size=5 , replace = F)
a
ifdf <- data.frame(mynum=1:6,
                   myvalue=sample(c("spring","bigdata","android"),size=6,replace=T)
                   )
ifdf

#myval
for(i in 1:nrow(ifdf)){
  if(ifdf[i,"myvalue"]=="spring"){
    ifdf[i,"info"] <-"프로젝트완료"
  }
  else{    
    ifdf[i,"info"] <-"할꺼야"
  }
}

#함수를 이용해서 같은 작업
ifdf[,"info2"] <- ifelse(test=ifdf$myvalue=="spring",yes="쉽다",no="할꺼다")
ifdf

ifdf[,"info3"] <- ifelse(test=ifdf$myvalue=="spring",yes="쉽다",no= ifelse(test=ifdf$myvalue=="bigdata",yes="빅데이터",no="앤드류"))
ifdf

#ifdf[,"info4"] <- "쉽다"
#ifdf[,"info4"] <- ifelse(test=ifdf$myvalue=="bigdata",yes="빅데이터",no="앤드류")
#ifdf
