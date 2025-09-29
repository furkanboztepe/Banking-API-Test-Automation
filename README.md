# Banking API - CI/CD Test Automation Project

Fintech şirketi için hazırlanmış Banking API test otomasyonu projesi.

## 🚀 Hızlı Başlangıç

### Gereksinimler
- Java 11+
- Maven 3.6+
- Git

### Testleri Çalıştır
```bash
# Tüm testleri çalıştır
mvn test

# Coverage raporu
mvn test jacoco:report
open target/site/jacoco/index.html
```

## 📊 GitHub'a Yükleme

```bash
# Git repository başlat
git init
git add .
git commit -m "Initial commit: Banking API with CI/CD"

# GitHub'da yeni repo oluştur: banking-api
# Sonra:
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/banking-api.git
git push -u origin main
```

## 🎯 Özellikler

- ✅ JUnit 5 ile 11 test senaryosu
- ✅ JaCoCo ile code coverage
- ✅ GitHub Actions CI/CD pipeline
- ✅ Security scanning (OWASP)
- ✅ Paralel test execution
- ✅ Test raporları ve artifacts

## 📁 Proje Yapısı

```
banking-api/
├── src/
│   ├── main/java/com/fintech/banking/
│   │   └── BankAccount.java
│   └── test/java/com/fintech/banking/
│       └── BankAccountTest.java
├── .github/workflows/
│   └── ci-cd.yml
├── pom.xml
└── README.md
```

## 🧪 Test Komutları

```bash
mvn test                              # Tüm testler
mvn test -Dtest=BankAccountTest      # Tek test
mvn clean test jacoco:report         # Coverage ile
```

## 📈 CI/CD Pipeline

Pipeline otomatik çalışır:
- Push to main/develop
- Pull Request
- Manuel tetikleme

### Pipeline Aşamaları:
1. Build & Test
2. Code Quality
3. Security Scan
4. Deploy (main branch)
5. Smoke Tests
6. Notifications

## 💡 Öğrenme Hedefleri

- Maven lifecycle
- JUnit 5 test yazma
- GitHub Actions YAML
- Test reporting (Surefire, JaCoCo)
- CI/CD best practices
- Security scanning

## 🎓 Pratik Egzersizler

1. Yeni test ekle ve pipeline'ı izle
2. Testi boz ve fail durumunu gör
3. Coverage'ı düşür ve uyarıyı gör
4. Yeni feature ekle (TDD)

---

Hazırlayan: Junior Test Automation Engineer için hazırlanmıştır.
