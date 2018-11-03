# API

## Requests

### **POST** - /sentence_boundary_detection

#### CURL

```sh
curl -X POST "http://localhost:4567/sentence_boundary_detection" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "sentence"="Kahvaltı sonrası Birbirinden güzel Savsat köylerini gezeceğiz. Otantik dokusu bozulmamış köylerin her biri birer tablo gibi. Ciritdüzü ilk göreceğimiz köy. Daha sonra Cevizli (Tibet ) köyündeki Tibeti Kilisesi'ni gezeceğiz."
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **sentence** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "Kahvaltı sonrası Birbirinden güzel Savsat köylerini gezeceğiz. Otantik dokusu bozulmamış köylerin her biri birer tablo gibi. Ciritdüzü ilk göreceğimiz köy. Daha sonra Cevizli (Tibet ) köyündeki Tibeti Kilisesi'ni gezeceğiz."
  ],
  "default": "Kahvaltı sonrası Birbirinden güzel Savsat köylerini gezeceğiz. Otantik dokusu bozulmamış köylerin her biri birer tablo gibi. Ciritdüzü ilk göreceğimiz köy. Daha sonra Cevizli (Tibet ) köyündeki Tibeti Kilisesi'ni gezeceğiz."
}
```

### **POST** - /find_pos

#### CURL

```sh
curl -X POST "http://localhost:4567/find_pos" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "sentence"="Gezimizin dokuzuncu gününde otelde alınan kahvaltı sonrası hareketle ilk olarak Hıdırlık Tepesi’nden Safranbolu’yu yukarıdan görerek rehberimizden bilgi alıyor ve Eski Hükümet Konağı, Cinci Hanı, Cinci Hamamı, Saat Kulesi’ni fotoğraflıyoruz"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **sentence** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "Gezimizin dokuzuncu gününde otelde alınan kahvaltı sonrası hareketle ilk olarak Hıdırlık Tepesi’nden Safranbolu’yu yukarıdan görerek rehberimizden bilgi alıyor ve Eski Hükümet Konağı, Cinci Hanı, Cinci Hamamı, Saat Kulesi’ni fotoğraflıyoruz"
  ],
  "default": "Gezimizin dokuzuncu gününde otelde alınan kahvaltı sonrası hareketle ilk olarak Hıdırlık Tepesi’nden Safranbolu’yu yukarıdan görerek rehberimizden bilgi alıyor ve Eski Hükümet Konağı, Cinci Hanı, Cinci Hamamı, Saat Kulesi’ni fotoğraflıyoruz"
}
```

### **POST** - /simple_tokenization

#### CURL

```sh
curl -X POST "http://localhost:4567/simple_tokenization" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "sentence"="3. Gün sonunda İstanbul'a, Merhaba saat 14:00 da 12 Ekim 2019"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **sentence** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "3. Gün sonunda İstanbul'a, Merhaba saat 14:00 da 12 Ekim 2019"
  ],
  "default": "3. Gün sonunda İstanbul'a, Merhaba saat 14:00 da 12 Ekim 2019"
}
```

### **POST** - /token_iterator

#### CURL

```sh
curl -X POST "http://localhost:4567/token_iterator" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "sentence"="#new_line Okulda beraber gittiğimiz @gknustr ile beraber yoldayız. 21.03.2018 tarihinde www.bearingcloud.com adrsine girilmiş." \
    --data-raw "show_input"="1" \
    --data-raw "token_mode"="all"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **sentence** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "#new_line Okulda beraber gittiğimiz @gknustr ile beraber yoldayız. 21.03.2018 tarihinde www.bearingcloud.com adrsine girilmiş."
  ],
  "default": "#new_line Okulda beraber gittiğimiz @gknustr ile beraber yoldayız. 21.03.2018 tarihinde www.bearingcloud.com adrsine girilmiş."
}
```
- **show_input** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "1"
  ],
  "default": "1"
}
```
- **token_mode** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "all"
  ],
  "default": "all"
}
```

### **POST** - /spelling_check

#### CURL

```sh
curl -X POST "http://localhost:4567/spelling_check" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "word"="Ankara'da" \
    --data-raw "show_input"="1"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **word** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "Ankara'da"
  ],
  "default": "Ankara'da"
}
```
- **show_input** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "1"
  ],
  "default": "1"
}
```

### **POST** - /spelling_suggestions

#### CURL

```sh
curl -X POST "http://localhost:4567/spelling_suggestions" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "word"="Ankara'da" \
    --data-raw "show_input"="1"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **word** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "Ankara'da"
  ],
  "default": "Ankara'da"
}
```
- **show_input** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "1"
  ],
  "default": "1"
}
```

### **POST** - /stems

#### CURL

```sh
curl -X POST "http://localhost:4567/stems" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "word"="Düşürttürmek" \
    --data-raw "show_input"="1"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **word** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "Düşürttürmek"
  ],
  "default": "Düşürttürmek"
}
```
- **show_input** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "1"
  ],
  "default": "1"
}
```

### **POST** - /lemmas

#### CURL

```sh
curl -X POST "http://localhost:4567/lemmas" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "word"="İzmir'de" \
    --data-raw "show_input"="1"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **word** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "İzmir'de"
  ],
  "default": "İzmir'de"
}
```
- **show_input** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "1"
  ],
  "default": "1"
}
```

### **POST** - /analyze_word

#### CURL

```sh
curl -X POST "http://localhost:4567/analyze_word" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "word"="Bodrum" \
    --data-raw "show_input"="1"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **word** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "Bodrum"
  ],
  "default": "Bodrum"
}
```
- **show_input** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "1"
  ],
  "default": "1"
}
```

### **POST** - /analyze_sentence

#### CURL

```sh
curl -X POST "http://localhost:4567/analyze_sentence" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "sentence"="Bodrum Otelleri" \
    --data-raw "show_input"="1" \
    --data-raw "disambiguate"="0" \
    --data-raw "deep_word_analysis"="0"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **sentence** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "Bodrum Otelleri"
  ],
  "default": "Bodrum Otelleri"
}
```
- **show_input** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "1"
  ],
  "default": "1"
}
```
- **disambiguate** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "0"
  ],
  "default": "0"
}
```
- **deep_word_analysis** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "0"
  ],
  "default": "0"
}
```

### **POST** - /generate_words

#### CURL

```sh
curl -X POST "http://localhost:4567/generate_words" \
    -H "Content-Type: application/x-www-form-urlencoded; charset=utf-8" \
    --data-raw "word"="yürümek" \
    --data-raw "morphemes"="Verb+Fut+Past+A3sg"
```

#### Header Parameters

- **Content-Type** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "application/x-www-form-urlencoded; charset=utf-8"
  ],
  "default": "application/x-www-form-urlencoded; charset=utf-8"
}
```

#### Body Parameters

- **word** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "yürümek"
  ],
  "default": "yürümek"
}
```
- **morphemes** should respect the following schema:

```
{
  "type": "string",
  "enum": [
    "Verb,Fut,Past,A3sg"
  ],
  "default": "Verb+Fut+Past+A3sg"
}
```

## References

