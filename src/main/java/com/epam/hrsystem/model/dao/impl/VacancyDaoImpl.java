package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.UserDao;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * VacancyDao implementation.
 *
 * @author Aleksey Klevitov
 */
public class VacancyDaoImpl implements VacancyDao {
    private static final ConnectionPool pool = ConnectionPool.ConnectionPoolHolder.POOL.getConnectionPool();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile VacancyDao instance;

    /**
     * Constructs a VacancyDaoImpl object.
     */
    private VacancyDaoImpl() {
    }

    /**
     * Returns a VacancyDao object.
     */
    public static VacancyDao getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new VacancyDaoImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean add(Vacancy vacancy) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_VACANCY)) {
            statement.setByte(1, vacancy.getIsAvailable() ? (byte) 1 : 0);
            statement.setString(2, vacancy.getPosition());
            statement.setString(3, vacancy.getDescription());
            statement.setDate(4, Date.valueOf(vacancy.getCreationDate()));
            statement.setLong(5, findCityIdByNameAndCountry(vacancy.getCity(), vacancy.getCountry()).orElseThrow(() -> new DaoException("Invalid city")));
            statement.setLong(6, vacancy.getEmployee().getId());
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateVacancyAvailability(long vacancyId, byte availabilityValue) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_VACANCY_AVAILABILITY)) {
            statement.setByte(1, availabilityValue);
            statement.setLong(2, vacancyId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Vacancy> findVacanciesByAvailability(boolean areAvailable) throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_VACANCIES_BY_AVAILABILITY)) {
            statement.setByte(1, areAvailable ? (byte) 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancies.add(createVacancyFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancies;
    }

    @Override
    public List<Vacancy> findEmployeeVacancies(long employeeId) throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_SELECT_EMPLOYEE_VACANCIES)) {
            statement.setLong(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancies.add(createVacancyFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancies;
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesByAvailability(long employeeId, boolean areAvailable) throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_SELECT_EMPLOYEE_VACANCIES_BY_AVAILABILITY)) {
            statement.setLong(1, employeeId);
            statement.setByte(2, areAvailable ? (byte) 1 : 0);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancies.add(createVacancyFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancies;
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithApplicantsRequests(long employeeId) throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_SELECT_EMPLOYEE_VACANCIES_WITH_APPLICANTS_REQUESTS)) {
            statement.setLong(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancies.add(createVacancyFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancies;
    }

    @Override
    public List<Vacancy> findEmployeeVacanciesWithApplicantsRequestsByActivity(long employeeId, boolean areActive) throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        String sqlQuery = areActive ? SqlQuery.SQL_SELECT_EMPLOYEE_VACANCIES_WITH_ACTIVE_APPLICANTS_REQUESTS : SqlQuery.SQL_SELECT_EMPLOYEE_VACANCIES_WITH_NOT_ACTIVE_APPLICANTS_REQUESTS;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setLong(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancies.add(createVacancyFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancies;
    }

    @Override
    public List<Vacancy> findVacanciesByKeyWord(String keyWord) throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_VACANCIES_BY_KEY_WORD)) {
            statement.setString(1, keyWord);
            statement.setString(2, keyWord);
            statement.setString(3, keyWord);
            statement.setString(4, keyWord);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancies.add(createVacancyFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancies;
    }

    @Override
    public boolean updateVacancyInfo(Vacancy vacancy) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_VACANCY_INFO)) {
            statement.setString(1, vacancy.getPosition());
            statement.setString(2, vacancy.getDescription());
            statement.setLong(3, findCityIdByNameAndCountry(vacancy.getCity(), vacancy.getCountry()).orElseThrow(() -> new DaoException("Invalid city")));
            statement.setLong(4, vacancy.getId());
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Vacancy> findVacancyById(long vacancyId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_VACANCY_BY_ID)) {
            statement.setLong(1, vacancyId);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(createVacancyFromResultSet(resultSet)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Long> findCountryIdByName(String name) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_COUNTRY_ID_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getLong(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Long> findCityIdByNameAndCountry(String city, String country) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_CITY_ID_BY_NAME)) {
            statement.setString(1, city);
            statement.setString(2, country);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getLong(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addCounty(String name) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_COUNTRY)) {
            statement.setString(1, name);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addCity(String city, String country) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_CITY)) {
            statement.setString(1, city);
            statement.setLong(2, findCountryIdByName(country).orElseThrow(() -> new DaoException("Invalid country")));
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean vacancyExists(Vacancy vacancy) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_CHECK_VACANCY_FOR_EXISTENCE)) {
            statement.setString(1, vacancy.getPosition());
            statement.setString(2, vacancy.getDescription());
            statement.setString(3, vacancy.getCountry());
            statement.setString(4, vacancy.getCity());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private Vacancy createVacancyFromResultSet(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(1);
        boolean isAvailable = resultSet.getByte(2) == 1;
        String position = resultSet.getString(3);
        String description = resultSet.getString(4);
        LocalDate creationDate = resultSet.getDate(5).toLocalDate();
        String country = resultSet.getString(6);
        String city = resultSet.getString(7);
        long userId = resultSet.getLong(8);
        User employee = userDao.findUserById(userId).orElseThrow(() -> new DaoException("Invalid id"));
        return (new Vacancy(id, isAvailable, position, description, creationDate, country, city, employee));
    }
}
