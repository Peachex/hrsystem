package com.epam.hrsystem.model.dao.impl;

import com.epam.hrsystem.exception.ConnectionPoolException;
import com.epam.hrsystem.exception.DaoException;
import com.epam.hrsystem.model.dao.SqlQuery;
import com.epam.hrsystem.model.dao.VacancyDao;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.entity.Vacancy;
import com.epam.hrsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum VacancyDaoImpl implements VacancyDao {
    INSTANCE;
    private static final ConnectionPool pool = ConnectionPool.POOL;

    @Override
    public boolean add(Vacancy vacancy) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_VACANCY)) {
            statement.setByte(1, vacancy.isAvailable() ? (byte) 1 : 0);
            statement.setString(2, vacancy.getPosition());
            statement.setString(3, vacancy.getDescription());
            statement.setDate(4, Date.valueOf(vacancy.getCreationDate()));
            statement.setLong(5, findCountryIdByName(vacancy.getCountry()).orElseThrow(() -> new DaoException("Invalid country")));
            statement.setLong(6, findCityIdByName(vacancy.getCity()).orElseThrow(() -> new DaoException("Invalid city")));
            statement.setLong(7, vacancy.getEmployee().getId());
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean updateVacancyAvailability(long vacancyId, byte availabilityValue) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_VACANCY_AVAILABILITY)) {
            statement.setByte(1, availabilityValue);
            statement.setLong(2, vacancyId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Vacancy> findAllVacancies() throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SqlQuery.SQL_SELECT_ALL_VACANCIES);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Vacancy vacancy = createVacancyFromResultSet(resultSet);
                vacancies.add(vacancy);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }

        return vacancies;
    }

    @Override
    public List<Vacancy> findDeletedVacancies() throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SqlQuery.SQL_SELECT_DELETED_VACANCIES);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Vacancy vacancy = createVacancyFromResultSet(resultSet);
                vacancies.add(vacancy);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancies;
    }

    @Override
    public List<Vacancy> findAvailableVacancies() throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SqlQuery.SQL_SELECT_AVAILABLE_VACANCIES);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Vacancy vacancy = createVacancyFromResultSet(resultSet);
                vacancies.add(vacancy);
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
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Vacancy vacancy = createVacancyFromResultSet(resultSet);
                vacancies.add(vacancy);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancies;
    }

    @Override
    public boolean updateVacancyInfo(Vacancy vacancy) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_UPDATE_VACANCY_INFO)) {
            statement.setString(1, vacancy.getPosition());
            statement.setString(2, vacancy.getDescription());
            statement.setLong(3, findCountryIdByName(vacancy.getCountry()).orElseThrow(() -> new DaoException("Invalid country")));
            statement.setLong(4, findCityIdByName(vacancy.getCity()).orElseThrow(() -> new DaoException("Invalid city")));
            statement.setLong(5, vacancy.getId());
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<Vacancy> findVacancyById(long vacancyId) throws DaoException {
        Optional<Vacancy> vacancy = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_VACANCY_BY_ID)) {
            statement.setLong(1, vacancyId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                vacancy = Optional.of(createVacancyFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return vacancy;
    }

    @Override
    public Optional<Long> findCountryIdByName(String name) throws DaoException {
        Optional<Long> id = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_COUNTRY_ID_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = Optional.of(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return id;
    }

    @Override
    public Optional<Long> findCityIdByName(String name) throws DaoException {
        Optional<Long> id = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_CITY_ID_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = Optional.of(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return id;
    }

    @Override
    public boolean addCounty(String name) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_COUNTRY)) {
            statement.setString(1, name);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean addCity(String name) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_CITY)) {
            statement.setString(1, name);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean vacancyExists(Vacancy vacancy) throws DaoException {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_CHECK_VACANCY_FOR_EXISTENCE)) {
            statement.setString(1, vacancy.getPosition());
            statement.setString(2, vacancy.getDescription());
            statement.setLong(3, findCountryIdByName(vacancy.getCountry()).orElseThrow(() -> new DaoException("Invalid country")));
            statement.setLong(4, findCityIdByName(vacancy.getCity()).orElseThrow(() -> new DaoException("Invalid city")));
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
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
        User employee = UserDaoImpl.INSTANCE.findUserById(userId).orElseThrow(() -> new DaoException("Invalid id"));
        Vacancy vacancy = new Vacancy(id, isAvailable, position, description, creationDate, country, city, employee);
        return vacancy;
    }
}
