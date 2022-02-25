package org.apache.http.conn.routing;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicRouteDirector implements HttpRouteDirector {
    @Override // org.apache.http.conn.routing.HttpRouteDirector
    public int nextStep(RouteInfo plan, RouteInfo fact) {
        if (plan == null) {
            throw new IllegalArgumentException("Planned route may not be null.");
        } else if (fact == null || fact.getHopCount() < 1) {
            int step = firstStep(plan);
            return step;
        } else if (plan.getHopCount() > 1) {
            int step2 = proxiedStep(plan, fact);
            return step2;
        } else {
            int step3 = directStep(plan, fact);
            return step3;
        }
    }

    protected int firstStep(RouteInfo plan) {
        return plan.getHopCount() > 1 ? 2 : 1;
    }

    protected int directStep(RouteInfo plan, RouteInfo fact) {
        if (fact.getHopCount() <= 1 && plan.getTargetHost().equals(fact.getTargetHost()) && plan.isSecure() == fact.isSecure()) {
            return (plan.getLocalAddress() == null || plan.getLocalAddress().equals(fact.getLocalAddress())) ? 0 : -1;
        }
        return -1;
    }

    protected int proxiedStep(RouteInfo plan, RouteInfo fact) {
        int phc;
        int fhc;
        if (fact.getHopCount() <= 1 || !plan.getTargetHost().equals(fact.getTargetHost()) || (phc = plan.getHopCount()) < (fhc = fact.getHopCount())) {
            return -1;
        }
        for (int i = 0; i < fhc - 1; i++) {
            if (!plan.getHopTarget(i).equals(fact.getHopTarget(i))) {
                return -1;
            }
        }
        if (phc > fhc) {
            return 4;
        }
        if ((fact.isTunnelled() && !plan.isTunnelled()) || (fact.isLayered() && !plan.isLayered())) {
            return -1;
        }
        if (plan.isTunnelled() && !fact.isTunnelled()) {
            return 3;
        }
        if (!plan.isLayered() || fact.isLayered()) {
            return plan.isSecure() != fact.isSecure() ? -1 : 0;
        }
        return 5;
    }
}
