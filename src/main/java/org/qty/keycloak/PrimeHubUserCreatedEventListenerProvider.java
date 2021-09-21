package org.qty.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;
import org.keycloak.events.admin.ResourceType;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class PrimeHubUserCreatedEventListenerProvider implements EventListenerProvider {
    private KeycloakSession session;

    public PrimeHubUserCreatedEventListenerProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        if (!(event.getResourceType() == ResourceType.USER
                && event.getOperationType() == OperationType.CREATE
                && event.getResourcePath().startsWith("users/"))) {
            return;
        }

        String userId = event.getResourcePath().split("/")[1];
        RealmModel realm = session.getContext().getRealm();
        UserModel user = session.users().getUserById(userId, realm);

        System.out.println(user.getUsername());
        System.out.println(user.getId());
        System.out.println(user.getCreatedTimestamp());

        // invoke GraphQL or joining the group directly
        // join to foobarbar group
        GroupModel group = realm.getGroupById("a962305b-c884-4413-9358-ef56373b287c");
        user.joinGroup(group);
    }

    @Override
    public void close() {

    }
}
