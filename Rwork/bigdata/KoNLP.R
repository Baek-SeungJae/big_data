install.packages("Sejong")
install.packages("hash")
install.packages("rJava")
install.packages("tau")
install.packages("RSQLite")
install.packages("devtools")
library(stringr)
library(KoNLP)
library(Sejong)
library(hash)
library(rJava)
library(tau)
library(RSQLite)
library(devtools)
useSejongDic()
extractNoun("롯데마트가 판매하고 있는 흑마늘 양념 치킨이 논란이 되고 있다.")
extractNoun(c("최악. 첫차앱 검증된 딜러라고 하지만 결국 일반 딜러와 다를바 없음. 모르면 돈뜯김 아니 알아도 뜯긴다. 어떻게든 안좋은 차를 좋게 포장해서 팔아야 하는게임. 우린 그걸 알아봐야 하는 게임. 작정하고 속이려는 수법이 나날로 발전하지만 차를 20번 30번씩 바꾸는게 아니면 알 도리가 없음. 특히 서서울모터리움(이*희) 절대 가지마라 재발 다들 당하고 큰돈 내고 배움(이라고 읽고 삥뜯긴다고 표현)을 겪을거다.", 
              "파는 사람은 모르겠는데, 거의 태반은 비싸게 올라오네요~~사는사람은 엔카가 나을듯요 K5 구모델보고 깜짝놀랬습니다. 뉴k5 가격이랑 차이없이 올려버리네요~~ 개인차팔이들도 성행하는듯 보이네요 직거래 미끼로 호구들만 낚여 후회할듯요~~ 엔카처럼 앱에서 평가나,시세에 대한 소비자에 대한 배려가 있었으면, 합니다. 파는사람은 비싸게 파니, 판매되는게 몇없겠고. 사는 사람은 보증도 안된 개인매물이 비싸니, 이앱에서 구매하겠습니까? 가격뿐 아니라,차에대한 성능분석표도 없네요.")
)

pos <- SimplePos09("롯데마트가 판매하고 있는 흑마늘 양념 치킨이 논란이 되고 있다.")
pos

SimplePos22("롯데마트가 판매하고 있는 흑마늘 양념 치킨이 논란이 되고 있다.")


######## 형태소 분석을 위해서 명사 분리 ########

length(comments)
length(score)
head(comments)
head(score)
sampledata <- comments[1:1000]
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
class(wordlist)
head(wordlist,10)

#table함수를 이용해서 단어의 빈도수 구하기
tablewordlist <- table(wordlist[2])
tablewordlist
tablewordlist[1]
tablewordlist[89]
names(tablewordlist)
sort(tablewordlist,decreasing = T)[1:100]
nchar("글자수")
tablewordlist_result <- tablewordlist[nchar(names(tablewordlist))>1]
tablewordlist_result <- sort(tablewordlist_result, decreasing = T)
tablewordlist_result
