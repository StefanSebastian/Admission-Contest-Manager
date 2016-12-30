using Microsoft.VisualStudio.TestTools.UnitTesting;
using contest.Validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using contest.Domain;

namespace contest.Validation.Tests
{
    [TestClass()]
    public class CandidateValidatorTests
    {
        /*
         * Validates a valid candidate - does not throw exception
         */
        [TestMethod()]
        public void validateValidCandidateTest()
        {
            CandidateValidator validator = new CandidateValidator();
            Candidate c = new Candidate(1, "name", "1231231230", "asd 123");
            try
            {
                validator.validate(c);
                Assert.IsTrue(true);       
            } catch (ValidatorException)
            {
                Assert.IsTrue(false);
            }
        }

        /*
         * Validates invalid id , id <= 0 , thorws exception
         */
        [TestMethod()]
        public void validateInvalidIdTest()
        {
            CandidateValidator validator = new CandidateValidator();
            Candidate c = new Candidate(-3, "name", "1231231230", "asd 1");
            try
            {
                validator.validate(c);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Id must be a positive integer\n");
            }
        }

        /*
         * Validates name : not empty, must contain only letters 
         */
        [TestMethod()]
        public void validateInvalidNameTest()
        {
            CandidateValidator validator = new CandidateValidator();
            Candidate c = new Candidate(1, "", "1231231230", "asd 123");
            try
            {
                validator.validate(c);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Name must not be empty.\n");
            }

            Candidate c1 = new Candidate(1, "name1", "1231231230", "asd 123");
            try
            {
                validator.validate(c1);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Name must contain only letters.\n");
            }
        }

        /*
         * Validates address : not empty
         */
        [TestMethod()]
        public void validateInvalidAddressTest()
        {
            CandidateValidator validator = new CandidateValidator();
            Candidate c = new Candidate(1, "name", "1231231230", "");
            try
            {
                validator.validate(c);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Address must not be empty.\n");
            }
        }

        /*
         * Validates telephone : not empty, contains only digits, has length of 10
         */
        [TestMethod()]
        public void validateInvalidTelephoneTest()
        {
            CandidateValidator validator = new CandidateValidator();
            Candidate c = new Candidate(1, "name", "", "ads");
            try
            {
                validator.validate(c);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Telephone must not be empty.\nTelephone must have 10 digits.\n");
            }

            Candidate c1 = new Candidate(1, "name", "123", "ads");
            try
            {
                validator.validate(c1);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Telephone must have 10 digits.\n");
            }

            Candidate c2 = new Candidate(1, "name", "123a", "ads");
            try
            {
                validator.validate(c2);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Telephone must have 10 digits.\nTelephone must contain only digits.\n");
            }

            Candidate c3 = new Candidate(1, "name", "123123123a", "ads");
            try
            {
                validator.validate(c3);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Telephone must contain only digits.\n");
            }
        }

        /*
         * Validates null pointer 
         */
        [TestMethod()]
        public void validateInvalidReferenceTest()
        {
            CandidateValidator validator = new CandidateValidator();
            Candidate c = new Candidate(1, "name", "1231231230", "asd 123");
            c = null;
            try
            {
                validator.validate(c);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exception)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exception.Message, "Candidate is null pointer");
            }
        }


    }
}