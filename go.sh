./gradlew clean build
kubectl -n hub cp ./build/libs/spi-lab-1.0-SNAPSHOT.jar keycloak-0:/opt/jboss/keycloak/standalone/deployments/primehub-user-created-event-provider.jar

