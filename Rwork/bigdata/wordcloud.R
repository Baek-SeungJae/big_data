install.packages("Sejong")
install.packages("hash")
install.packages("rJava")
install.packages("tau")
install.packages("RSQLite")
install.packages("devtools")
install.packages("wordcloud")
install.packages("RColorBrewer")
library(stringr)
library(KoNLP)
library(Sejong)
library(hash)
library(rJava)
library(tau)
library(RSQLite)
library(devtools)
library(wordcloud)
library(RColorBrewer)
useSejongDic()
extractNoun("롯데마트가 판매하고 있는 흑마늘 양념 치킨이 논란이 되고 있다.")
extractNoun(c("R은 free 소프트웨어이고, [완전하게 무보증]입니다.", 
              "일정한 조건에 따르면, 자유롭게 이것을 재배포할수가 있습니다.")
)

pos <- SimplePos09("롯데마트가 판매하고 있는 흑마늘 양념 치킨이 논란이 되고 있다.")
pos

SimplePos22("롯데마트가 판매하고 있는 흑마늘 양념 치킨이 논란이 되고 있다.")


######## 형태소 분석을 위해서 명사 분리 ########

length(comments)
length(score)
head(comments)
head(score)
sampledata <- comments[1:5000]
data_list = list() # 댓글을 분리하면 분리된 명사의 갯수가 다르므로 리스트를 이용해야 한다.
for(i in 1:length(sampledata)){
  data <- SimplePos09(sampledata[i])
  data_list[[i]] <- data
}
head(data_list,20)
#/로 분할 - 리스트의 모든 요소에 저장된 문자열을 /로 분리 => N이 있는 문자열의 첫번재 요소 가져오기
# sapply를 이용하면 반복작업을 할 수 있다.
class(data_list)
# class를 unlist로 변환함
class(unlist(data_list))

wordlist <- sapply(str_split(data_list,"/"), function(x){
                                                x[1]
})
wordlist <- gsub("(.*\\\")","",wordlist)
class(wordlist)
head(wordlist,10)
#table함수를 이용해서 단어의 빈도수 구하기
tablewordlist <- table(wordlist)
tablewordlist[1]
tablewordlist[89]
names(tablewordlist)
sort(tablewordlist,decreasing = T)[1:100]
nchar("글자수")
tablewordlist_result <- tablewordlist[nchar(names(tablewordlist))>1]
tablewordlist_result <- sort(tablewordlist_result,decreasing = T)
tablewordlist_result

#RColorBrewer
display.brewer.all(type = "div")
display.brewer.all(type = "qual")
display.brewer.all(type = "seq")

word <- names(tablewordlist_result)
count <- as.numeric(tablewordlist_result)
mycolor <- brewer.pal(n=9,name="Set1")
wordcloud(words = word, freq = count, random.order = F, colors = mycolor,scale=c(7,0,3))
