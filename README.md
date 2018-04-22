# - Zemberek Türkçe Doğal Dil İşleme Docker Sunucusu 
## Zemberek Turkish NLP Dockerized REST Server 
Türkçe Doğal Dil İşleme konusunda en iyi Java araçlardan olan zemberek'in Sparkjava ile REST sunucusu. Sisteminize Java vb bağlılıkları kurmadan kolayca Türkçe NLP kaynaklara erişebilirsiniz.

## Zemberek
[`Zemberek`](https://github.com/ahmetaa/zemberek-nlp) [`Zemberek Örnekler`](https://github.com/ahmetaa/turkish-nlp-examples)

## [`Spark`](http://sparkjava.com/)
Spark varsayılan olarak 4567 portları üzerinde çalışıyor.

## Kurulum
``` 
docker build -t zemberek-test .
docker run -p 4567:4567 zemberek-test
```

## Kullanım 
Projeyi docker ile çalıştırdıktan sonra curl, wget veya kullandığınız dildeki kütüphaneler (faraday vb) ile çağırabilirsiniz. 

## Endpointler
http://localhost:4567/find_pos?sentence=Bu bizim ilk denememiz


![Örnek Endpoint Ekran Görüntüsü](/docs/endpoint-screenshot.png)

** Proje beta sürümündedir **
@WIP
