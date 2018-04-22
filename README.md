# - Zemberek Türkçe Doğal Dil İşleme Docker Sunucusu 
## Zemberek Turkish NLP Dockerized REST Server 
Türkçe Doğal Dil İşleme konusunda en iyi Java araçlardan olan zemberek'in Sparkjava ile bağlanmış docker dosyası. 
Spark varsayılan olarak 4567 portları üzerinde çalışıyor.

## Zemberek
[`Zemberek`](https://github.com/ahmetaa/zemberek-nlp) [`Zemberek Örnekler`](https://github.com/ahmetaa/turkish-nlp-examples)

## Kullanım
Projeyi docker ile çalıştırdıktan sonra curl, wget veya kullandığınız dildeki kütüphaneler (faraday vb) ile çağırabilirsiniz. 

``` 
docker build -t zemberek-test .
docker run -p 4567:4567 zemberek-test
```
## Endpointler
http://localhost:4567/find_pos?sentence=Bu bizim ilk denememiz


![Örnek Endpoint Ekran Görüntüsü](/docs/endpoint-screenshot.png)

** Proje beta sürümündedir **
@WIP
