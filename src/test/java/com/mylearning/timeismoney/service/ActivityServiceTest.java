package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.ActivityDao;
import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.impl.JdbcActivityDao;
import com.mylearning.timeismoney.dao.impl.JdbcDaoFactory;
import com.mylearning.timeismoney.entity.Activity;
import com.mylearning.timeismoney.exception.ServiceLogicException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ActivityServiceTest {

    private final ActivityDao mockActivityDao = Mockito.mock(JdbcActivityDao.class);
    private final DaoFactory mockDaoFactory = Mockito.mock(JdbcDaoFactory.class);
    private final ActivityService activityService = new ActivityService(mockDaoFactory);

    @Before
    public void createDao() {
        Mockito.when(mockDaoFactory.createActivityDao())
                .thenReturn(mockActivityDao);
    }

    @Test
    public void activityCreate() {
        Mockito.when(mockActivityDao.create(Mockito.any()))
                .thenReturn(true);


        Activity activity = new Activity();
        boolean creationResult = false;

        try {
            creationResult = activityService.create(activity);
        } catch (ServiceLogicException e) {
            e.printStackTrace();
        }

        assertTrue(creationResult);
    }

    @Test
    public void findById() {
        Activity activity = new Activity.Builder().id(0).build();

        Mockito.when(mockActivityDao.findById(0))
                .thenReturn(Optional.of(activity));

        Activity result = activityService.findById(0);

        assertEquals(activity, result);
    }

    @Test
    public void findPageableByState() {
        List<Activity> activities = Arrays.asList(
                new Activity.Builder().id(0).build(),
                new Activity.Builder().id(1).build(),
                new Activity.Builder().id(2).build());

        Mockito.when(mockActivityDao.findPageableByState(1,1,true))
                .thenReturn(activities);

        List<Activity> result = activityService.findPageableByState(1, 1, true);

        assertEquals(activities, result);
    }

    @Test
    public void findActiveProxy() {
        List<Activity> activities = Arrays.asList(
                new Activity.Builder().id(0).isArchived(false).build(),
                new Activity.Builder().id(1).isArchived(false).build());

        Mockito.when(mockActivityDao.findActiveProxy())
                .thenReturn(activities);

        List<Activity> result = activityService.findActiveProxy();

        assertEquals(activities, result);
    }

    @Test
    public void archiveById() {
        Mockito.when(mockActivityDao.updateStateById(0, false))
                .thenReturn(true);

        boolean result = false;

        try {
            result = activityService.archiveById(0);
        } catch (ServiceLogicException e) {
            e.printStackTrace();
        }

        assertTrue(result);
    }

    @Test
    public void activateById() {
        Mockito.when(mockActivityDao.updateStateById(0, true))
                .thenReturn(true);

        boolean result = false;

        try {
            result = activityService.activateById(0);
        } catch (ServiceLogicException e) {
            e.printStackTrace();
        }

        assertTrue(result);
    }

    @Test
    public void getActiveCount() {
        Mockito.when(mockActivityDao.getCountByState(true))
                .thenReturn(10);

        int result = activityService.getActiveCount();

        assertEquals(10, result);
    }

    @Test
    public void getArchivedCount() {
        Mockito.when(mockActivityDao.getCountByState(false))
                .thenReturn(11);

        int result = activityService.getArchivedCount();

        assertEquals(11, result);
    }

    @Test
    public void update() {
        Activity activity = new Activity.Builder().id(0).build();

        Mockito.when(mockActivityDao.update(activity))
                .thenReturn(true);

        activity.setId(1);
        boolean updateResult = false;

        try {
            updateResult = activityService.update(activity);
        } catch (ServiceLogicException e) {
            e.printStackTrace();
        }

        assertTrue(updateResult);
    }

}