package ua.gov.cyberpolice.breach.repository;

import org.springframework.data.jpa.domain.Specification;
import ua.gov.cyberpolice.breach.entity.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class FindSpecification {

    public static Specification<Breach> applyFindListFilters(Find find) {
        return (root, query, builder) -> {
            Collection<Predicate> predicates = new ArrayList<>(10);

//            CriteriaQuery<Payment> paymentQuery = builder.createQuery(Payment.class);
//            Root<Payment> employee = query.from(Payment.class);
//            Root<BankCardPayment> bankCardPayment = builder.treat(employee, BankCardPayment.class);
//            Root<WebsitePayment> websitePayment = builder.treat(employee, WebsitePayment.class);

            Root<Participant> participantRoot = query.from(Participant.class);
            Root<BankCard> bankCardRoot = query.from(BankCard.class);
            Root<Person> personRoot = query.from(Person.class);


            if(!"".equals(find.getPassport())) {
                predicates.add(
                        builder.like(
                                bankCardRoot.get("holder").get("passport"),
                                find.getPassport()));
                predicates.add(
                        builder.like(
                                participantRoot.get("person").get("passport"),
                                find.getPassport()));
            }
//            if(!"".equals(find.getLastName())) {
//                Path<Person> statusJoin = bankCardRoot.get("holder");
//                Predicate status = builder.like(statusJoin.get("lastName"), find.getLastName());
//                predicates.add(status);
//            }



            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

//    public static Specification<ReportTransaction> applyTransactionFilters(ZonedDateTime fromDate, ZonedDateTime toDate, TransactionFilter filter) {
//        return (root, query, builder) -> {
//            Collection<Predicate> predicates = new ArrayList<>(7);
//
//            predicates.add(builder.between(root.get("createdDate"), fromDate, toDate));
//
//            if (StringUtils.isNotEmpty(filter.getStatus())) {
//                String[] statuses = filter.getStatus().split(",");
//                Expression<String> transactionStatusExp = root.get("transactionStatus");
//                Predicate transactionStatus = builder.and(transactionStatusExp.in(statuses));
//                predicates.add(transactionStatus);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getOperation())) {
//                String[] operations = filter.getOperation().split(",");
//                Expression<String> transactionOperationExp = root.get("transactionOperation");
//                Predicate transactionOperation = builder.and(transactionOperationExp.in(operations));
//                predicates.add(transactionOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getType())) {
//                String[] types = filter.getType().split(",");
//                Expression<String> typeExp = root.get("transactionType");
//                Predicate typeOperation = builder.and(typeExp.in(types));
//                predicates.add(typeOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getMerchantId())) {
//                String[] merchants = filter.getMerchantId().split(",");
//                Expression<String> merchantExp = root.get("merchantId");
//                Predicate merchantOperation = builder.and(merchantExp.in(merchants));
//                predicates.add(merchantOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getPaymentMethod())) {
//                String[] paymentMethods = filter.getPaymentMethod().split(",");
//                Expression<String> paymentMethodExp = root.get("paymentMethod");
//                Predicate paymentMethodOperation = builder.and(paymentMethodExp.in(paymentMethods));
//                predicates.add(paymentMethodOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getPaymentGateway())) {
//                String[] paymentGateways = filter.getPaymentGateway().split(",");
//                Expression<String> paymentGatewayExp = root.get("gatewayName");
//                Predicate paymentGatewayOperation = builder.and(paymentGatewayExp.in(paymentGateways));
//                predicates.add(paymentGatewayOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getErrorCode())) {
//                String[] responseCodes = filter.getErrorCode().split(",");
//                Expression<String> responseCodeExp = root.get("responseCode");
//                Predicate responseCodeOperation = builder.and(responseCodeExp.in(responseCodes));
//                predicates.add(responseCodeOperation);
//            }
//
//            final String filterValue = filter.getFilterValue();
//            if (StringUtils.isNotEmpty(filter.getFilterField()) && StringUtils.isNotEmpty(filterValue)) {
//
//                switch (filter.getFilterField()) {
//                    case "transactionUUID":
//                        Predicate transactionId = builder.equal(root.get("transactionUUID"), filterValue);
//                        predicates.add(transactionId);
//                        break;
//                    case "customerEmail":
//                        Predicate customerEmail = builder.equal(root.get("customerEmail"), filterValue);
//                        predicates.add(customerEmail);
//                        break;
//                    case "referenceNo":
//                        Predicate referenceNo = builder.equal(root.get("incomingTrId"), filterValue);
//                        predicates.add(referenceNo);
//                        break;
//                    case "gatewayTransactionId":
//                        Predicate gatewayTransactionId = builder.equal(root.get("gatewayTransactionId"), filterValue);
//                        predicates.add(gatewayTransactionId);
//                        break;
//                    case "customData":
//                        Predicate customData = builder.equal(root.get("isettleTrId"), filterValue);
//                        predicates.add(customData);
//                        break;
//                    case "cardNum":
//                        Join<Transaction, Customer> customerPanJoin = root.join("customer");
//                        Predicate customerPan = builder.like(customerPanJoin.get("cardNumber"), "%" + filterValue + "%");
//                        predicates.add(customerPan);
//                        break;
//                }
//            }
//            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
//    }
//
//    public static Specification<TransactionList> applyTransactionFiltersForList(ZonedDateTime fromDate, ZonedDateTime toDate, TransactionFilter filter) {
//        return (root, query, builder) -> {
//            Collection<Predicate> predicates = new ArrayList<>(7);
//
//            predicates.add(builder.between(root.get("createdDate"), fromDate, toDate));
//
//            if (StringUtils.isNotEmpty(filter.getStatus())) {
//                String[] statuses = filter.getStatus().split(",");
//                Expression<String> transactionStatusExp = root.get("transactionStatus");
//                Predicate transactionStatus = builder.and(transactionStatusExp.in(statuses));
//                predicates.add(transactionStatus);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getOperation())) {
//                String[] operations = filter.getOperation().split(",");
//                Expression<String> transactionOperationExp = root.get("transactionOperation");
//                Predicate transactionOperation = builder.and(transactionOperationExp.in(operations));
//                predicates.add(transactionOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getType())) {
//                String[] types = filter.getType().split(",");
//                Expression<String> typeExp = root.get("transactionType");
//                Predicate typeOperation = builder.and(typeExp.in(types));
//                predicates.add(typeOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getMerchantId())) {
//                String[] merchants = filter.getMerchantId().split(",");
//                Expression<String> merchantExp = root.get("merchantId");
//                Predicate merchantOperation = builder.and(merchantExp.in(merchants));
//                predicates.add(merchantOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getPaymentMethod())) {
//                String[] paymentMethods = filter.getPaymentMethod().split(",");
//                Expression<String> paymentMethodExp = root.get("paymentMethod");
//                Predicate paymentMethodOperation = builder.and(paymentMethodExp.in(paymentMethods));
//                predicates.add(paymentMethodOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getPaymentGateway())) {
//                String[] paymentGateways = filter.getPaymentGateway().split(",");
//                Expression<String> paymentGatewayExp = root.get("gatewayName");
//                Predicate paymentGatewayOperation = builder.and(paymentGatewayExp.in(paymentGateways));
//                predicates.add(paymentGatewayOperation);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getErrorCode())) {
//                String[] responseCodes = filter.getErrorCode().split(",");
//                Expression<String> responseCodeExp = root.get("responseCode");
//                Predicate responseCodeOperation = builder.and(responseCodeExp.in(responseCodes));
//                predicates.add(responseCodeOperation);
//            }
//
//            final String filterValue = filter.getFilterValue();
//            if (StringUtils.isNotEmpty(filter.getFilterField()) && StringUtils.isNotEmpty(filterValue)) {
//
//                switch (filter.getFilterField()) {
//                    case "transactionUUID":
//                        Predicate transactionId = builder.equal(root.get("transactionUUID"), filterValue);
//                        predicates.add(transactionId);
//                        break;
//                    case "customerEmail":
//                        Predicate customerEmail = builder.equal(root.get("customerEmail"), filterValue);
//                        predicates.add(customerEmail);
//                        break;
//                    case "referenceNo":
//                        Predicate referenceNo = builder.equal(root.get("incomingTrId"), filterValue);
//                        predicates.add(referenceNo);
//                        break;
//                    case "gatewayTransactionId":
//                        Predicate gatewayTransactionId = builder.equal(root.get("gatewayTransactionId"), filterValue);
//                        predicates.add(gatewayTransactionId);
//                        break;
//                    case "customData":
//                        Predicate customData = builder.equal(root.get("isettleTrId"), filterValue);
//                        predicates.add(customData);
//                        break;
//                    case "cardNum":
//                        Join<Transaction, Customer> customerPanJoin = root.join("customer");
//                        Predicate customerPan = builder.like(customerPanJoin.get("cardNumber"), "%" + filterValue + "%");
//                        predicates.add(customerPan);
//                        break;
//                }
//            }
//            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
//    }
//
//    public static Specification<Transaction> applyKYCFilters(ZonedDateTime fromDate, ZonedDateTime toDate, KycFilter filter) {
//        return (root, query, builder) -> {
//            Collection<Predicate> predicates = new ArrayList<>();
//
//            predicates.add(builder.between(root.get("createdDate"), fromDate, toDate));
//            predicates.add(builder.isNotNull(root.get("customer")));
//
//            if (StringUtils.isNotEmpty(filter.getStatus())) {
//                Join<Transaction, Customer> customerJoin = root.join("customer");
//                Join<Customer, KycStatus> statusJoin = customerJoin.join("kycStatus");
//                Specific.common(builder, predicates, filter.getStatus().split(","), statusJoin, "code");
//            }
//
//            if (StringUtils.isNotEmpty(filter.getKycMerchantId())) {
//                Join<Transaction, Merchant> merchantJoin = root.join("merchant");
//                Specific.common(builder, predicates, filter.getKycMerchantId().split(","), merchantJoin, "id");
//            }
//
//            if (StringUtils.isNotEmpty(filter.getEmail())) {
//
//                String[] customerEmails = filter.getEmail().split(",");
//
//                Join<Transaction, Customer> customerJoin = root.join("customer");
//
//                if (customerEmails.length > 1) {
//                    List<Predicate> emailPredicates = new ArrayList<>();
//                    for (String email : customerEmails) {
//                        Predicate predicate = builder.like(customerJoin.get("email"), email);
//                        emailPredicates.add(predicate);
//                    }
//                    predicates.add(builder.or(emailPredicates.toArray(new Predicate[0])));
//                } else {
//                    predicates.add(builder.like(customerJoin.get("email"), customerEmails[0] + "%"));
//                }
//            }
//
//            if (StringUtils.isNotEmpty(filter.getCurrency())) {
//                Join<Transaction, Currency> currencyJoin = root.join("currency");
//                Predicate currPredicate = builder.equal(currencyJoin.get("codeLiteral"),
//                        filter.getCurrency());
//                predicates.add(currPredicate);
//            }
//
//            if (StringUtils.isNotEmpty(filter.getAmountFilterType()) && filter.getAmount() != null) {
//
//                switch (filter.getAmountFilterType()) {
//                    case "greaterThan":
//                        predicates.add(builder.greaterThan(root.get("amount"), filter.getAmount()));
//                        break;
//                    case "lessThan":
//                        predicates.add(builder.lessThan(root.get("amount"), filter.getAmount()));
//                        break;
//                    case "equals":
//                        predicates.add(builder.equal(root.get("amount"), filter.getAmount()));
//                        break;
//                }
//            }
//            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
//    }
}