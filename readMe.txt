-projet avec Spring Security:
-Déclarer la dépendance de Spring Security dans le fichier POM:
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		
-Déclarer aussi la dépendance JWT:
-Modifier la classe SecurityConfig
-Ajouter un contrôleur "AuthController" pour intercepter les paramètres
 de connexion (login/password). L'accès à cette API est public
-Ajouter un filtre qui s'exécute en premier lieu pour vérifier le token 's'il existe)
-Ajouter une classe "JwtService" pour générer et vérifier le token JWT
-Ajouter des classes DTO "AuthRequest" et "AuthResponse" pour transférer le contenu JSON


*Pour le test

*********************
Pour obtenir le jeton (user/user123)
---------------------
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"user\",\"password\":\"user123\"}"
  
*********************
Tester en mode GET
---------------------
curl -i -X GET http://localhost:8080/api/products ^
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTc1MjQwNDMxNSwiZXhwIjoxNzUyNDkwNzE1fQ.tlraW5Yla7P7_AX3Kac3o8OyTFgcs1wROL4ljEuPdcEop00vuQk7ydpO_44RuXawQ1JaPpUxjJUZd4B1q1jIgg"

*********************
Pour obtenir le jeton (admin/admin123)
---------------------
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}"
  
*********************
Tester en mode POST
---------------------
curl -i -X POST http://localhost:8080/api/products ^
  -H "Authorization: Bearer $TOKEN" ^
  -H "Content-Type: application/json"  ^
  -d "{\"name\":\"New Product\",\"price\":42.50}"
