package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.MissionDao;
import com.mylearning.timeismoney.entity.Mission;
import com.mylearning.timeismoney.entity.enums.MissionField;
import com.mylearning.timeismoney.entity.enums.MissionState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MissionServiceTest {

    private final MissionDao mockMissionDao = Mockito.mock(MissionDao.class);
    private final DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
    private final MissionService missionService = new MissionService(mockDaoFactory);

    @Before
    public void createDao() {
        Mockito.when(mockDaoFactory.createMissionDao())
                .thenReturn(mockMissionDao);
    }

    @Test
    public void missionCreate() {
        Mockito.when(mockMissionDao.create(Mockito.any()))
                .thenReturn(true);

        boolean creationResult = missionService.create(new Mission());

        assertTrue(creationResult);
    }

    @Test
    public void findPageable() {
        List<Mission> missions = Arrays.asList(
                new Mission.Builder().id(0).build(),
                new Mission.Builder().id(1).build());

        Mockito.when(mockMissionDao.findPageableSortedBy(1, 1, MissionState.ACTIVE, MissionField.USER_ID))
                .thenReturn(missions);

        List<Mission> result = missionService.findPageable(1, 1, MissionState.ACTIVE, MissionField.USER_ID);


        assertEquals(missions, result);
    }

    @Test
    public void countByState() {
        Mockito.when(mockMissionDao.countByState(MissionState.ACTIVE))
                .thenReturn(2);

        int result = missionService.countByState(MissionState.ACTIVE);

        assertEquals(2, result);
    }

    @Test
    public void updateState() {
        Mission mission = new Mission.Builder()
                .state(MissionState.ACTIVE)
                .build();

        Mockito.when(mockMissionDao.updateState(mission, MissionState.PASSED))
                .thenReturn(true);

        boolean updateResult = missionService.updateState(mission, MissionState.PASSED);

        assertTrue(updateResult);
    }
}