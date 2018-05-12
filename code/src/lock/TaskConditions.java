package lock;

import java.util.Objects;

public class TaskConditions {
    private Integer id;
    private Integer configId;
    private String taskName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskConditions(Integer id, Integer configId, String taskName) {
        this.id = id;
        this.configId = configId;
        this.taskName = taskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskConditions that = (TaskConditions) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(configId, that.configId) &&
                Objects.equals(taskName, that.taskName);
    }
}
