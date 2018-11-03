
# Zemberek Türkçe Doğal Dil İşleme Docker Mikroservis Sunucusu 
## Zemberek Turkish NLP Dockerized REST Microservice Server
Türkçe eklemeli bir dil olduğu için Hint-Avrupa dilleri için geliştirilmiş araçlar Türkçe için iyi sonuç vermemektedir. 

Türkçe Doğal Dil İşleme konusunda en iyi Java araçlardan olan zemberek'in Sparkjava ile mikroservis REST sunucusu. Sisteminize Java vb bağlılıkları uğraşmadan Python, Ruby, Php, JavaScript vb dillerle kolayca Türkçe Doğal Dil İşleme işlemlerinizi yapabilirsiniz.

## Zemberek
[`Zemberek`](https://github.com/ahmetaa/zemberek-nlp) [`Zemberek Örnekler`](https://github.com/ahmetaa/turkish-nlp-examples). Projede mevcut olarak Zemberek'in 0.15.0 sürümü kullanılmaktadır.

## Mikroservis REST sunucusu - Spark
[`Spark`](http://sparkjava.com/) varsayılan olarak 4567 portları üzerinde çalışıyor.

## Kurulum
Dockerhub üzerindeki son sürümü kullanmak için;
``` 
docker pull cbilgili/zemberek-nlp-server:1.2
docker run -p 4567:4567 cbilgili/zemberek-nlp-server:1.2
``` 
Yerel geliştirme ortamında çalıştırmak için;
``` 
git clone https://github.com/cbilgili/zemberek-nlp-server.git
cd zemberek-nlp-server
mvn clean install
docker build -t zemberek-nlp-server .
docker run -p 4567:4567 zemberek-nlp-server
```
Bunun ardından http://localhost:4567 üzerinden endpointlere erişebilirsiniz.

## Kullanım 
Projeyi docker ile çalıştırdıktan sonra curl, wget veya kullandığınız dildeki kütüphaneler (faraday vb) ile çağırabilirsiniz. Proje içindeki [Postman (v2)](api_postman_v2.json) ve [PAW](api_paw.paw) dosyasını indirip endpointleri test edebilirsiniz.

## API Endpointleri
* [Cümle Sınırı Denetleme (Sentence Boundary Detection)](API.md) : `POST /sentence_boundary_detection`
* [Cümlenin Öğeleri Etiketleme (Part of Speech Tagging)](API.md) : `POST /find_pos`
* [Temel Cümle Öğelerine Ayırma (Simple Tokenization)](API.md) : `POST /simple_tokenization`
* [Cümle Öğelerine Ayırma (Token Iterator)](API.md) : `POST /token_iterator`
* [Yazım Kontrolü (Spelling Check)](API.md) : `POST /spelling_check`
* [Yazım Önerimi (Spelling Suggestions)](API.md) : `POST /spelling_suggestions`
* [Kelime Kökleri (Stemming)](API.md) : `POST /stems`
* [Kelime Kökleri (Lemmas)](API.md) : `POST /lemmas`
* [Kelime Analizi (Analyze Word)](API.md) : `POST /analyze_word`
* [Cümle Analizi (Analyze Sentence)](API.md) : `POST /analyze_sentence`
* [Kelime Oluşturma (Generate Word)](API.md) : `POST /generate_word`

![Örnek Endpoint Ekran Görüntüsü](/docs/endpoint-screenshot.png)
