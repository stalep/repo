package io.hyperfoil.tools.horreum.svc;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.hyperfoil.tools.horreum.api.alerting.Change;
import io.hyperfoil.tools.horreum.api.data.Action;
import io.hyperfoil.tools.horreum.api.data.DataSet;
import io.hyperfoil.tools.horreum.api.data.Run;
import io.hyperfoil.tools.horreum.api.data.Test;
import io.hyperfoil.tools.horreum.entity.data.ActionDAO;
import io.hyperfoil.tools.horreum.entity.data.TestDAO;
import io.hyperfoil.tools.horreum.events.DatasetChanges;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.Instant;

@ApplicationScoped
public class ServiceMediator {

    @Inject
    private TestServiceImpl testService;

    @Inject
    private AlertingServiceImpl alertingService;

    @Inject
    private RunServiceImpl runService;

    @Inject
    private ReportServiceImpl reportService;

    @Inject
    private ExperimentServiceImpl experimentService;

    @Inject
    private LogServiceImpl logService;

    @Inject
    private SubscriptionServiceImpl subscriptionService;

    @Inject
    private ActionServiceImpl actionService;

    @Inject
    private NotificationServiceImpl notificationService;

    @Inject
    private DatasetServiceImpl datasetService;

    @Inject
    private EventAggregator aggregator;

    public ServiceMediator() {
    }


    @Transactional
    void newTest(Test test) {
        actionService.onNewTest(test);
    }

    @Transactional
    void deleteTest(Test test) {
       actionService.onTestDelete(test);
       alertingService.onTestDeleted(test);
       experimentService.onTestDeleted(test);
       logService.onTestDelete(test);
       reportService.onTestDelete(test);
       runService.onTestDeleted(test);
       subscriptionService.onTestDelete(test);
    }

    @Transactional
    void newRun(Run run) {
       actionService.onNewRun(run);
       alertingService.removeExpected(run);
    }

    @Transactional
    void deleteDataSet(DataSet.Info info) {
        alertingService.onDatasetDeleted(info);
    }

    void newDataSet(DataSet.EventNew eventNew) {
        datasetService.onNewDataset(eventNew);
    }

    @Transactional
    void updateDataSet(DataSet.LabelsUpdatedEvent updatedEvent) {
       alertingService.onLabelsUpdated(updatedEvent);
    }

    @Transactional
    void missingValuesDataset(MissingValuesEvent event) {
        notificationService.onMissingValues(event);
    }

    @Transactional
    void newDatasetChanges(DatasetChanges changes) {
        notificationService.onNewChanges(changes);
    }

    @Transactional
    void newChange(Change.Event event) {
        actionService.onNewChange(event);
        aggregator.onNewChange(event);
    }

    TestDAO getTestForUpdate(int testId) {
        return testService.getTestForUpdate(testId);
    }

    TestDAO ensureTestExists(String testNameOrId, String token) {
        return testService.ensureTestExists(testNameOrId, token);
    }

    void notifyMissingDataset(int testId, String ruleName, long maxStaleness, Instant timestamp) {
        notificationService.notifyMissingDataset(testId, ruleName, maxStaleness, timestamp);
    }

    void notifyExpectedRun(int testId, long expectedBefore, String expectedBy, String backLink) {
        notificationService.notifyExpectedRun(testId, expectedBefore, expectedBy, backLink);
    }

    int transform(int runId, boolean isRecalculation) {
      return runService.transform(runId, isRecalculation);
    }

    void withRecalculationLock(Runnable run) {
        datasetService.withRecalculationLock(run);
    }

    void validate(Action dto) {
        actionService.validate(dto);
    }

    void merge(ActionDAO dao) {
        actionService.merge(dao);
    }

    void exportTest(ObjectNode export, int testId) {
        export.set("alerting", alertingService.exportTest(testId));
        export.set("actions", actionService.exportTest(testId));
        export.set("experiments", experimentService.exportTest(testId));
        export.set("subscriptions", subscriptionService.exportSubscriptions(testId));
    }

    void importTestToAll(Integer id, JsonNode alerting, JsonNode actions, JsonNode experiments,
                         JsonNode subscriptions, boolean forceUseTestId) {
        alertingService.importTest(id, alerting, forceUseTestId);
        actionService.importTest(id, actions, forceUseTestId);
        experimentService.importTest(id, experiments, forceUseTestId);
        subscriptionService.importSubscriptions(id, subscriptions);
    }
}
