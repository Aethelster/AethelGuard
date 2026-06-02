# Aethelguard

> Paper tabanlı Minecraft sunucuları için temiz loglu, özelleştirilebilir ve güvenli bir auth eklentisi.

Aethelguard, oyuncular sunucuya girdiğinde onları doğrulama tamamlanana kadar kontrollü bir güvenlik ekranında tutar. Oyuncu captcha, login, register veya 2FA aşamasındayken dünyaya, chate ve riskli komutlara erişemez; doğrulama tamamlandığında da kaldığı yerden güvenli şekilde oyuna döner.

Amaç basit: Auth sistemi güçlü olsun, console temiz kalsın, sunucu sahibi de neredeyse her davranışı configten rahatça ayarlayabilsin. ✨

## 🚀 Neler Sunar?

- 🔐 `/register` ve `/login` tabanlı hesap sistemi
- 🧂 BCrypt ile güvenli şifre hashleme
- 💾 Local YAML veya MySQL depolama
- 🧩 `/aethelguard status` ile detaylı oyuncu auth bilgileri
- 🛡️ Captcha sistemi: map, text, numeric, alphanumeric ve math türleri
- 🗺️ Varsayılan map captcha desteği
- 📱 TOTP tabanlı 2FA: Google Authenticator, Microsoft Authenticator, Authy ve benzeri uygulamalarla uyumlu
- 🔁 Session auto-login desteği
- 🌍 Login/register öncesi oyuncuyu void-zone alanına alma
- 📍 Login sonrası son güvenli konuma geri döndürme
- 🏠 İlk register sonrası spawn noktasına gönderme
- 🎒 Auth ekranında oyuncunun envanterini, zırhını ve offhand itemini gizleme
- 🧼 Auth sonrası oyuncunun kendi chat ekranını temizleme
- 💬 Login ekranındaki oyuncuların normal chati görmesini engelleme
- 📊 Auth aşamasına göre bossbar gösterme
- 🔇 Vanilla join/quit/connection/command loglarını susturma
- 📝 Sadece önemli auth olaylarını Aethelguard formatında loglama
- 🌐 Oyuncu mesajları için `messages_<code>.yml` dil sistemi
- ⚙️ Yeni config ayarlarını otomatik ekleyen ve config düzenini commentleriyle koruyan sistem

## 🛠️ Kurulum

1. Jar dosyasını sunucunun `plugins` klasörüne at.
2. Sunucuyu başlat.
3. Oluşan `plugins/Aethelguard/config.yml` dosyasını kendi sunucuna göre düzenle.
4. Mesajları değiştirmek istersen `plugins/Aethelguard/messages/` içindeki dil dosyalarını düzenle.
5. Database veya temel auth davranışlarını değiştirdiysen sunucuyu yeniden başlat.

Ayarların çoğu reload ile yenilenebilir:

```text
/aethelguard reload
```

## ⌨️ Komutlar

| Komut | Aliaslar | Açıklama |
| --- | --- | --- |
| `/register <şifre> <şifre tekrar>` | `/kayitol`, `/kayıtol`, `/kayit` | Yeni oyuncu hesabı oluşturur. |
| `/login <şifre>` | `/giris`, `/giriş` | Kayıtlı hesaba giriş yapar. |
| `/captcha <kod>` | `/dogrula`, `/doğrula` | Captcha doğrulamasını tamamlar. |
| `/twofactor <kod>` | `/2fa`, `/authenticator`, `/authy` | Login sırasında 2FA kodunu doğrular. |
| `/twofactor setup` | `/2fa setup` | Oyuncunun hesabına authenticator kurulumunu başlatır. |
| `/twofactor confirm <kod>` | `/2fa confirm <kod>` | 2FA kurulumunu tamamlar. |
| `/twofactor disable <kod>` | `/2fa disable <kod>` | Oyuncunun 2FA korumasını kapatır. |
| `/changepassword <eski> <yeni> <yeni tekrar>` | `/sifredegistir`, `/şifredeğiştir`, `/password` | Oyuncunun kendi şifresini değiştirmesini sağlar. |

## 👑 Admin Komutları

| Komut | Açıklama |
| --- | --- |
| `/aethelguard reload` | Config, dil dosyaları ve database bağlantısını yeniler. |
| `/aethelguard status <oyuncu>` | Oyuncunun kayıt, login, IP, konum, yanlış deneme ve depolama durumunu gösterir. |
| `/aethelguard sessions` | Aktif session listesini gösterir. |
| `/aethelguard session <oyuncu>` | Oyuncunun aktif session bilgisini gösterir. |
| `/aethelguard clearsession <oyuncu>` | Bir oyuncunun session kaydını temizler. |
| `/aethelguard clearsessions` | Tüm session kayıtlarını temizler. |
| `/aethelguard unregister <oyuncu>` | Oyuncunun auth kaydını siler. |
| `/aethelguard changepassword <oyuncu> <yeniŞifre>` | Bir oyuncunun şifresini admin olarak değiştirir. |
| `/aethelguard unlogin <oyuncu>` | Online oyuncuyu oyundan atmadan tekrar auth ekranına alır. |

Admin aliasları:

```text
/ag
/aeg
/ayarlar
```

## 🧭 Auth Akışı

Oyuncu sunucuya girdiğinde Aethelguard önce güvenli konumunu hatırlar. Ardından oyuncu auth ekranına alınır.

Varsayılan akış:

1. Oyuncu void-zone konumuna taşınır.
2. Envanteri, zırhları ve offhand itemi geçici olarak gizlenir.
3. Captcha gerekiyorsa önce captcha çözmesi istenir.
4. Hesabı yoksa `/register`, hesabı varsa `/login` istenir.
5. Hesapta 2FA açıksa `/2fa <kod>` istenir.
6. Auth tamamlanınca bossbar kaldırılır, envanter geri verilir ve oyuncu güvenli konumuna döner.

İlk kayıt sonrasında oyuncu spawn noktasına gönderilir. Oyuncu auth olmadan çıkarsa veya timeout yerse void-zone konumu son konum olarak kaydedilmez.

## 🛡️ Captcha

Captcha login/register öncesinde çalışır. Varsayılan tür `MAP` captchadır.

Desteklenen türler:

- `MAP`
- `TEXT`
- `NUMERIC`
- `ALPHANUMERIC`
- `MATH`

Configte varsayılan:

```yml
auth-settings:
  captcha:
    enabled: true
    types: ["MAP"]
```

Birden fazla tür yazarsan plugin captcha türünü rastgele seçebilir:

```yml
types: ["MAP", "TEXT", "NUMERIC", "ALPHANUMERIC", "MATH"]
```

Captcha deneme hakkı ve kick ayarı, login yanlış şifre denemesinden ayrıdır. Böylece captcha için 5 deneme, login için 3 deneme gibi farklı güvenlik kuralları kullanabilirsin.

## 📡 2FA / Authenticator

Aethelguard TOTP standardını kullanır. Bu yüzden şu uygulamalarla uyumludur:

- Google Authenticator
- Microsoft Authenticator
- Authy
- TOTP destekleyen benzer uygulamalar

Oyuncu kurulumu:

```text
/2fa setup
/2fa confirm <kod>
```

Sonraki girişlerde doğru şifre girildikten sonra oyuncudan authenticator kodu istenir:

```text
/2fa <kod>
```

## 🔒 Config Sistemi

Aethelguard config dosyasını sunucu açılışında ve reload sırasında kontrol eder.

Sistem şunları yapar:

- Eksik config keylerini ekler.
- Mevcut değerleri korur.
- Yeni eklenen ayarları dosyanın en altına atmaz.
- Ayarları gerçek config şablonundaki doğru bölüme taşır.
- Commentleri koruyarak dosyayı düzenli hale getirir.
- Bilinen ayarlar yanlış yerdeyse doğru yere taşır ve console’a mavi log basar.

Bu sayede eski config kullanan bir sunucu yeni sürüme geçtiğinde ayarlarını kaybetmeden güncel config yapısına yaklaşır.

## 📞 Dil Sistemi

Oyuncu mesajları `messages_<code>.yml` dosyalarından okunur.

Varsayılan dosyalar:

- `messages_tr.yml`
- `messages_en.yml`

Özel dil eklemek için:

1. `plugins/Aethelguard/messages/messages_de.yml` gibi bir dosya oluştur.
2. İçine mevcut mesaj keylerini ekle.
3. Çevirileri düzenle.
4. Configte dili değiştir:

```yml
default-language: "DE"
```

`default-language` özel dil dosyalarını destekler. `console-language` ise yalnızca plugin console logları içindir ve sadece `en` / `tr` mantığıyla çalışır.

## 🗒 Console Dili

Console logları için ayrı dil ayarı bulunur:

```yml
console-language: "tr"
console-text-mode: "ascii"
```

`console-text-mode` sadece `console-language: "tr"` iken kullanılır.

- `native`: Türkçe karakterleri olduğu gibi yazar. Örnek: `başarıyla giriş yaptı`
- `ascii`: Türkçe karakterleri console-safe hale getirir. Örnek: `basariyla giris yapti`

Hosting panelin veya console ekranın Türkçe karakterleri bozuyorsa `ascii` kullanman önerilir.

## 📝 Log Sistemi

Aethelguard gereksiz vanilla log kalabalığını azaltabilir.

Filtrelenebilen loglar:

- `joined the game`
- `left the game`
- `logged in with entity id`
- `lost connection`
- `UUID of player`
- `issued server command`

`/login`, `/register` gibi şifre içeren komutlar console’a şifreyle düşmesin diye ayrıca sessiz işlenir.

Auth başarıları, auto-login, unregister, unlogin ve benzeri durumlar Aethelguard loglarıyla takip edilebilir.

## 🛢 Depolama

Local kullanımda oyuncu kayıtları şu klasörde tutulur:

```text
plugins/Aethelguard/users/
```

Local modda ayrıca username ve UUID eşleşmeleri için index dosyası oluşturulabilir:

```text
plugins/Aethelguard/users/user-index.txt
```

MySQL kullanmak için:

```yml
database:
  enabled: true
  host: "localhost"
  port: 3306
  database: "aethelguard_db"
  username: "root"
  password: "password123"
```

Tablo adı yalnızca harf, sayı ve alt çizgi içermelidir.

## 🔑 İzinler

| Permission | Açıklama |
| --- | --- |
| `aethelguard.admin` | Admin komutlarını kullanma izni. Varsayılan: OP |
| `aethelguard.bypass.iplimit` | IP kayıt limitini bypass eder. Varsayılan: OP |

## 📰 Sürüm Notları

0.2-sentinel sonrası geliştirme sürecinde öne çıkan yenilikler:

- Captcha sistemi
- Map captcha görünümü
- Captcha cooldown ve ayrı attempt/kick ayarları
- Authenticator / 2FA desteği
- Bossbar auth yönlendirmeleri
- Auth ekranında envanter gizleme
- Login/register/captcha promptları için ayrı tekrar ayarları
- Console text mode: `native` / `ascii`
- Config auto-sync sistemi
- Yeni config keylerini doğru yere commentleriyle ekleme

0.3 sürümü tamamlandığında bu özellikler final sürüm notlarına ayrıca toparlanacak.

## 📜 Lisans

Aethelguard, **Aethelguard Public Use License** ile paylaşılır.

Kısaca:

- Plugin kişisel, public veya ticari Minecraft sunucularında ücretsiz kullanılabilir.
- Orijinal ve değiştirilmemiş hali paylaşılabilir.
- Kaynak kod inceleme, öğrenme ve güvenlik kontrolü için okunabilir.
- Değiştirilmiş sürüm yayınlamak, fork dağıtmak veya kodu başka projede kullanmak yasaktır.
- Aethelguard’ın sahipliği ve telif hakları Aethelster’a aittir.

Detaylar için `LICENSE` dosyasını okuyabilirsin.
