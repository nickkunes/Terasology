package org.terasology.entitySystem;

import org.terasology.entitySystem.pojo.persistence.EntityPersister;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Immortius <immortius@gmail.com>
 */
public interface EntityManager {

    void registerComponentClass(Class<? extends Component> componentClass);

    public enum SaveFormat {
        Binary,
        Text,
        JSON
    }

    //TODO: Remove save/load from here, should be external to Entity Manager
    void save(File file, SaveFormat format) throws IOException;
    void load(File file, SaveFormat format) throws IOException;
    void clear();

    // Entity Management

    /**
     * @return A references to a new, unused entity
     */
    EntityRef create();

    /**
     * @param prefabName The name of the prefab to create.
     * @return A new entity, based on the the prefab of the given name. If the prefab doesn't exist, just a new entity.
     */
    EntityRef create(String prefabName);

    /**
     * @param prefab
     * @return A new entity, based on the given prefab
     */
    EntityRef create(Prefab prefab);

    /**
     * @param componentClass
     * @return The number of entities with this component class
     */
    int getComponentCount(Class<? extends Component> componentClass);

    Iterable<EntityRef> iteratorEntities();

    Iterable<EntityRef> iteratorEntities(Class<? extends Component> ...  componentClasses);

    <T extends Component> Iterable<Map.Entry<EntityRef,T>> iterateComponents(Class<T> componentClass);


    /**
     * @return The event system being used by the entity manager
     */
    EventSystem getEventSystem();

    void setEventSystem(EventSystem system);

    PrefabManager getPrefabManager();

    void setPrefabManager(PrefabManager prefabManager);

    EntityPersister getPersister();

}
