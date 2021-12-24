package ru.belov.webapplication.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import ru.belov.webapplication.model.User;

@Slf4j
@Repository
public class UserDao {

    private static final String NOT_CONFIRM_MAIL = "SELECT u.UserID, u.Name, u.Pass, u.Mail FROM tUser AS u "
            + "WHERE NOT EXISTS "
            + "(SELECT * FROM tAudit AS a WHERE a.ActionType  = ? AND a.UserID = u.UserID);";

    private static final String NOT_LOGIN = "SELECT u.UserID, u.Name, u.Pass, u.Mail FROM tUser AS u "
            + "WHERE NOT EXISTS "
            + "(SELECT * FROM tAudit AS a WHERE a.ActionType  = ? AND a.UserID = u.UserID);";

    private static final String ACTIVE_USERS = "SELECT u.UserId, u.Name, u.Pass, u.Mail "
            + "FROM tAccessToken as a "
            + "JOIN tUser as u ON a.UserID = u.UserID "
            + "WHERE a.ExpireDate > CURRENT_TIMESTAMP;";

    private static final String TOP_LOGIN_FAIL = "SELECT u.UserID, u.Name, u.Pass, u.Mail "
            + "FROM tAudit as a JOIN tUser AS u ON a.UserID = u.UserID "
            + "WHERE a.ActionType = ? GROUP BY u.UserID "
            + "ORDER BY COUNT(a.ActionType) "
            + "DESC LIMIT ?;";

    @Value("${Registration}")
    private int registration;

    @Value("${CheckMail}")
    private int checkMail;

    @Value("${LogInPass}")
    private int logInPass;

    @Value("${LogOut}")
    private int logOut;

    @Value("${LoginFail}")
    private int loginFail;

    @Value("${LimitTopRows}")
    private int limitTopRows;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findNotConfirmMail() {
        log.debug("Find users which no confirm mail, with Action Type = {}", checkMail);
        return jdbcTemplate.query(NOT_CONFIRM_MAIL, new BeanPropertyRowMapper<>(User.class), checkMail);
    }

    public List<User> findNotLogin() {
        log.debug("Find users which never login, with Action Type = {}", logInPass);
        return jdbcTemplate.query(NOT_LOGIN, new BeanPropertyRowMapper<>(User.class), logInPass);
    }

    public List<User> findUsersActiveToken() {
        log.debug("Find users which have active token");
        return jdbcTemplate.query(ACTIVE_USERS, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findTopFailLogin() {
        log.debug("Find users which more often enter invalid password, with Action Type = {}, Limit rows = {}",
                loginFail, limitTopRows);
        return jdbcTemplate.query(TOP_LOGIN_FAIL, new BeanPropertyRowMapper<>(User.class), loginFail, limitTopRows);
    }

}
