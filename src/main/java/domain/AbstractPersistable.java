package domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;

/**
 * AbstractPersistable
 */
public abstract class AbstractPersistable {

    private Long id;

    protected AbstractPersistable() {
    }

    protected AbstractPersistable(Long id) {
        this.id = id;
    }

    @PlanningId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}