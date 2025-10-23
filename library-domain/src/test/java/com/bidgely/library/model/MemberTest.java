package com.bidgely.library.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("M001", "John Doe", "john.doe@example.com", 
                           "1234567890", LocalDate.of(2023, 1, 1), MembershipType.PREMIUM);
    }

    @Test
    void testMemberCreation() {
        assertThat(member.getMemberId()).isEqualTo("M001");
        assertThat(member.getName()).isEqualTo("John Doe");
        assertThat(member.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(member.getPhoneNumber()).isEqualTo("1234567890");
        assertThat(member.getMembershipDate()).isEqualTo(LocalDate.of(2023, 1, 1));
        assertThat(member.getMembershipType()).isEqualTo(MembershipType.PREMIUM);
        assertThat(member.isActive()).isTrue();
    }

    @Test
    void testGetMaxBooksAllowed() {
        assertThat(member.getMaxBooksAllowed()).isEqualTo(5);
    }

    @Test
    void testGetLoanDurationDays() {
        assertThat(member.getLoanDurationDays()).isEqualTo(21);
    }

    @Test
    void testSetActive() {
        member.setActive(false);
        assertThat(member.isActive()).isFalse();
    }

    @Test
    void testEquals_sameMemberId() {
        Member member2 = new Member("M001", "Jane Doe", "jane.doe@example.com",
                                   "9876543210", LocalDate.of(2023, 2, 1), MembershipType.BASIC);
        assertThat(member).isEqualTo(member2);
    }

    @Test
    void testEquals_differentMemberId() {
        Member member2 = new Member("M002", "John Doe", "john.doe@example.com",
                                   "1234567890", LocalDate.of(2023, 1, 1), MembershipType.PREMIUM);
        assertThat(member).isNotEqualTo(member2);
    }

    @Test
    void testHashCode() {
        Member member2 = new Member("M001", "Jane Doe", "jane.doe@example.com",
                                   "9876543210", LocalDate.of(2023, 2, 1), MembershipType.BASIC);
        assertThat(member.hashCode()).isEqualTo(member2.hashCode());
    }

    @Test
    void testToString() {
        String result = member.toString();
        assertThat(result).contains("M001");
        assertThat(result).contains("John Doe");
        assertThat(result).contains("john.doe@example.com");
    }

    @Test
    void testSetters() {
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");
        member.setPhoneNumber("9876543210");
        member.setMembershipType(MembershipType.STUDENT);
        member.setMembershipDate(LocalDate.of(2023, 6, 1));

        assertThat(member.getName()).isEqualTo("Jane Doe");
        assertThat(member.getEmail()).isEqualTo("jane.doe@example.com");
        assertThat(member.getPhoneNumber()).isEqualTo("9876543210");
        assertThat(member.getMembershipType()).isEqualTo(MembershipType.STUDENT);
        assertThat(member.getMembershipDate()).isEqualTo(LocalDate.of(2023, 6, 1));
    }

    @Test
    void testDifferentMembershipTypes() {
        Member basicMember = new Member("M002", "Basic User", "basic@example.com",
                                       "1111111111", LocalDate.now(), MembershipType.BASIC);
        assertThat(basicMember.getMaxBooksAllowed()).isEqualTo(3);
        assertThat(basicMember.getLoanDurationDays()).isEqualTo(14);

        Member studentMember = new Member("M003", "Student User", "student@example.com",
                                         "2222222222", LocalDate.now(), MembershipType.STUDENT);
        assertThat(studentMember.getMaxBooksAllowed()).isEqualTo(4);
        assertThat(studentMember.getLoanDurationDays()).isEqualTo(14);

        Member facultyMember = new Member("M004", "Faculty User", "faculty@example.com",
                                         "3333333333", LocalDate.now(), MembershipType.FACULTY);
        assertThat(facultyMember.getMaxBooksAllowed()).isEqualTo(10);
        assertThat(facultyMember.getLoanDurationDays()).isEqualTo(30);
    }
}

