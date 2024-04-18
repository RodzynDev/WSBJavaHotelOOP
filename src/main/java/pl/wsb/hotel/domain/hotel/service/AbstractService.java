package pl.wsb.hotel.domain.hotel.service;

import pl.wsb.hotel.domain.client.Client;

import java.time.LocalDateTime;

public abstract class AbstractService {
    private final String name;
    private Client requester = null;
    private final boolean isForPremiumClients;

    protected AbstractService(String name, boolean isForPremiumClients) {
        this.name = name;
        this.isForPremiumClients = isForPremiumClients;
    }

    protected abstract void orderService();

    // TODO: Implement logic of checking service availability
    abstract void checkServiceAvailability(LocalDateTime orderTime);

    public String getName() {
        return name;
    }

    public void setRequester(Client requester) {
        this.requester = requester;
    }

    public Client getRequester() {
        return this.requester;
    }

    public boolean isForPremiumClients() {
        return this.isForPremiumClients;
    }
    protected void getfullinfoinpl(){
        System.out.println("Nazwa usługi "+this.name+" jest wykonywana dla klienta premium "+this.isForPremiumClients);
    }
}
