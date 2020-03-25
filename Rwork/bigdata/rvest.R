library(rvest)
url <- "https://datalab.naver.com/"

readPage <- read_html(url,encoding = "UTF-8")
readPage %>% 
  html_nodes("span.subject_fixed") %>% html_text() -> data
data

readPage <- read_html(url,encoding="UTF-8")
readPage %>% 
  html_nodes("span.title")

#html_
#html_
#html_
#html_children()
#html_attr()