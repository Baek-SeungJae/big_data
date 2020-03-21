data("mpg")
#### 1 ####
mpg %>% 
  group_by(class) %>% 
  summarise(cty_avg = mean(cty))

#### 2 ####
mpg %>% 
  group_by(class) %>% 
  summarise(cty_avg = mean(cty)) %>%
  arrange(desc(cty_avg))

#### 3 ####
mpg %>% 
  group_by(manufacturer) %>% 
  summarise(hwy_avg = mean(hwy)) %>%
  arrange(desc(hwy_avg)) %>% 
  head(3)

#### 4 ####
mpg %>% 
  group_by(manufacturer) %>% 
  filter(class=="compact") %>% 
  summarise(compact_count = n()) %>% 
  arrange(desc(compact_count))
