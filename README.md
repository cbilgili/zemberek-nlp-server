# Zemberek Türkçe Doğal Dil İşleme Docker Mikroservis Sunucusu 
## Zemberek Turkish NLP Dockerized REST Microservice Server
Türkçe eklemeli bir dil olduğu için Hint-Avrupa dilleri için geliştirilmiş araçlar Türkçe için iyi sonuç vermemektedir. 

Türkçe Doğal Dil İşleme konusunda en iyi Java araçlardan olan zemberek'in Sparkjava ile mikroservis REST sunucusu. Sisteminize Java vb bağlılıkları uğraşmadan Python, Ruby, Php, JavaScript vb dillerle kolayca Türkçe Doğal İşleme işlemlerinizi yapabilirsiniz.

## Zemberek
[`Zemberek`](https://github.com/ahmetaa/zemberek-nlp) [`Zemberek Örnekler`](https://github.com/ahmetaa/turkish-nlp-examples)

## Mikroservis REST sunucusu - Spark
[`Spark`](http://sparkjava.com/) varsayılan olarak 4567 portları üzerinde çalışıyor.

## Kurulum
``` 
git clone https://github.com/cbilgili/zemberek-nlp-server.git
cd zemberek-nlp-server
docker build -t zemberek-nlp-server .
docker run -p 4567:4567 zemberek-nlp-server
```
Bunun ardından http://localhost:4567 üzerinden endpointlere erişebilirsiniz.

## Kullanım 
Projeyi docker ile çalıştırdıktan sonra curl, wget veya kullandığınız dildeki kütüphaneler (faraday vb) ile çağırabilirsiniz. 

**Part of Speech Etiketleme**
----
  POS bilgisini döner

* **URL**

  /find_pos

* **Method:**

  `POST`
  
*  **URL Params**


* **Data Params**

  **Required:**
 
    `sentence=[text]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/find_pos",
      dataType: "json",
      type : "POST",
      data: { 
        'foo': '[text]'
      }
      success : function(r) {
        console.log(r);
      }
    });
  ```


![Örnek Endpoint Ekran Görüntüsü](/docs/endpoint-screenshot.png)

** Proje beta sürümündedir **
@WIP
