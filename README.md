### Описание
Задания на SQL.

1. Найти всех пользователей, которые зарегистрировались, но не подтвердили почту.
2. Найти всех пользователей у которых просрочился токен доступа, текущую дату время из функции GetDate()
3. Найти всех пользователей которые зарегистрировались, но не разу не заходили в систему.
4. Найти первых четырех пользователей, которые чаще всего неверно ввродят пароль.

Задание на Java.

Спроектировать WEB сервис (Spring MVC)без View, только котролееры/репозиторий и прочие слои для сущности User.
Подключить логирование.

### SQL  
/*ActionType: 1 - Registration, 2 - CheckMail, 3 - LogInPass, 4 - LogOut, 5 - LoginFail*/  
  
**1. Найти всех пользователей, которые зарегистрировались, но не подтвердили почту.**  
  
	SELECT u.UserID, u.Name, u.Pass, u.Mail FROM tUser AS u WHERE NOT EXISTS  
	   (SELECT * FROM tAudit AS a WHERE a.ActionType  = 2 AND a.UserID = u.UserID);  
  
**2. Найти всех пользователей у которых просрочился токен доступа, текущую дату время из функции GetDate().**  
  
	SELECT u.UserId, u.Name, u.Pass, u.Mail  
	   FROM tAccessToken as a  
	   JOIN tUser as u ON a.UserID = u.UserID  
	   WHERE a.ExpireDate < CURRENT_TIMESTAMP; 
  
**3. Найти всех пользователей которые зарегистрировались, но не разу не заходили в систему.**  
  
	SELECT u.UserID, u.Name, u.Pass, u.Mail FROM tUser AS u  
	   WHERE NOT EXISTS  
	  (SELECT * FROM tAudit AS a WHERE a.ActionType  = 3 AND a.UserID = u.UserID);  
  
**4. Найти первых четырех пользователей, которые чаще всего неверно ввродят пароль**  
  
	SELECT u.UserID, u.Name, u.Pass, u.Mail  
            FROM tAudit as a JOIN tUser AS u ON a.UserID = u.UserID  
            WHERE a.ActionType = 5  
            GROUP BY u.UserID   
            ORDER BY COUNT(a.ActionType)  
            DESC LIMIT 4;  

### EndPoints
**1. Найти всех пользователей, которые зарегистрировались, но не подтвердили почту.**  
http://localhost:8080/webapplication/user/not-confirm-mail  
**2. Найти всех пользователей у которых просрочился токен доступа, текущую дату время из функции GetDate()**  
http://localhost:8080/webapplication/user/active-token  
**3. Найти всех пользователей которые зарегистрировались, но не разу не заходили в систему.**  
http://localhost:8080/webapplication/user/not-login  
**4. Найти первых четырех пользователей, которые чаще всего неверно ввродят пароль**  
http://localhost:8080/webapplication/user/top-fail-login
