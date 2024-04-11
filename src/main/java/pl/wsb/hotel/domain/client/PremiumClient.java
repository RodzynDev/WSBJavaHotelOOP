package pl.wsb.hotel.domain.client;

import java.time.LocalDate;

public class PremiumClient extends Client {

    private PremiumClientType premiumAccountType;

    public PremiumClient(String id, String firstName, String lastName, LocalDate birthDate) {
        super(id, firstName, lastName, birthDate);
        this.premiumAccountType = PremiumClientType.PREMIUM;
    }

    public PremiumClient(String id, String firstName, String lastName, LocalDate birthDate, PremiumClientType premiumAccountType) {
        super(id, firstName, lastName, birthDate);
        this.premiumAccountType = premiumAccountType;
    }

    @Override()
    public String getFullName()
    {
        return "[Premium] " + super.getFullName();
    }

    @Override()
    public String getAllSummaries() throws Exception {
        return super.getAllSummaries()
                + "Premium account type: " + this.getPremiumAccountType() + "\n";
    }

    public PremiumClientType getPremiumAccountType() {
        return premiumAccountType;
    }

    public void setPremiumAccountType(PremiumClientType premiumAccountType) {
        this.premiumAccountType = premiumAccountType;
    }
}
