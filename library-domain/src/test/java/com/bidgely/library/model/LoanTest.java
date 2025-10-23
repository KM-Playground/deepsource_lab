package com.bidgely.library.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LoanTest {

    private Loan loan;

    @BeforeEach
    void setUp() {
        loan = new Loan("L001", "M001", "978-0-13-468599-1",
                       LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 15));
    }

    @Test
    void testLoanCreation() {
        assertThat(loan.getLoanId()).isEqualTo("L001");
        assertThat(loan.getMemberId()).isEqualTo("M001");
        assertThat(loan.getIsbn()).isEqualTo("978-0-13-468599-1");
        assertThat(loan.getLoanDate()).isEqualTo(LocalDate.of(2023, 10, 1));
        assertThat(loan.getDueDate()).isEqualTo(LocalDate.of(2023, 10, 15));
        assertThat(loan.getStatus()).isEqualTo(LoanStatus.ACTIVE);
        assertThat(loan.getFineAmount()).isEqualTo(0.0);
        assertThat(loan.getReturnDate()).isNull();
    }

    @Test
    void testIsOverdue_whenNotOverdue() {
        Loan futureLoan = new Loan("L002", "M001", "978-0-13-468599-1",
                                   LocalDate.now(), LocalDate.now().plusDays(7));
        assertThat(futureLoan.isOverdue()).isFalse();
    }

    @Test
    void testIsOverdue_whenOverdue() {
        Loan overdueLoan = new Loan("L003", "M001", "978-0-13-468599-1",
                                    LocalDate.now().minusDays(20), LocalDate.now().minusDays(5));
        assertThat(overdueLoan.isOverdue()).isTrue();
    }

    @Test
    void testIsOverdue_whenReturned() {
        loan.setStatus(LoanStatus.RETURNED);
        assertThat(loan.isOverdue()).isFalse();
    }

    @Test
    void testGetDaysOverdue_whenNotOverdue() {
        Loan futureLoan = new Loan("L002", "M001", "978-0-13-468599-1",
                                   LocalDate.now(), LocalDate.now().plusDays(7));
        assertThat(futureLoan.getDaysOverdue()).isEqualTo(0);
    }

    @Test
    void testGetDaysOverdue_whenOverdue() {
        Loan overdueLoan = new Loan("L003", "M001", "978-0-13-468599-1",
                                    LocalDate.now().minusDays(20), LocalDate.now().minusDays(5));
        assertThat(overdueLoan.getDaysOverdue()).isGreaterThan(0);
    }

    @Test
    void testSetReturnDate() {
        LocalDate returnDate = LocalDate.of(2023, 10, 14);
        loan.setReturnDate(returnDate);
        assertThat(loan.getReturnDate()).isEqualTo(returnDate);
    }

    @Test
    void testSetStatus() {
        loan.setStatus(LoanStatus.RETURNED);
        assertThat(loan.getStatus()).isEqualTo(LoanStatus.RETURNED);
    }

    @Test
    void testSetFineAmount() {
        loan.setFineAmount(5.0);
        assertThat(loan.getFineAmount()).isEqualTo(5.0);
    }

    @Test
    void testEquals_sameLoanId() {
        Loan loan2 = new Loan("L001", "M002", "978-0-13-468599-2",
                             LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 15));
        assertThat(loan).isEqualTo(loan2);
    }

    @Test
    void testEquals_differentLoanId() {
        Loan loan2 = new Loan("L002", "M001", "978-0-13-468599-1",
                             LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 15));
        assertThat(loan).isNotEqualTo(loan2);
    }

    @Test
    void testHashCode() {
        Loan loan2 = new Loan("L001", "M002", "978-0-13-468599-2",
                             LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 15));
        assertThat(loan.hashCode()).isEqualTo(loan2.hashCode());
    }

    @Test
    void testToString() {
        String result = loan.toString();
        assertThat(result).contains("L001");
        assertThat(result).contains("M001");
        assertThat(result).contains("978-0-13-468599-1");
    }

    @Test
    void testSetters() {
        loan.setLoanId("L999");
        loan.setMemberId("M999");
        loan.setIsbn("978-0-13-468599-9");
        loan.setLoanDate(LocalDate.of(2023, 11, 1));
        loan.setDueDate(LocalDate.of(2023, 11, 15));

        assertThat(loan.getLoanId()).isEqualTo("L999");
        assertThat(loan.getMemberId()).isEqualTo("M999");
        assertThat(loan.getIsbn()).isEqualTo("978-0-13-468599-9");
        assertThat(loan.getLoanDate()).isEqualTo(LocalDate.of(2023, 11, 1));
        assertThat(loan.getDueDate()).isEqualTo(LocalDate.of(2023, 11, 15));
    }
}

