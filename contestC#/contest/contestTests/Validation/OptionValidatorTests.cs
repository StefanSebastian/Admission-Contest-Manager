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
    public class OptionValidatorTests
    {
        /*
         * Validates valid option - does not throw exception
         */
        [TestMethod()]
        public void validateValidOptionTest()
        {
            OptionValidator validator = new OptionValidator();
            Department department = new Department(1, "name", 100);
            Candidate candidate = new Candidate(1, "name", "1231231230", "asd 123");
            Option opt = new Option(candidate, department);
            try
            {
                validator.validate(opt);
                Assert.IsTrue(true);
            } catch (ValidatorException exc)
            {
                Assert.IsTrue(false);
            }
        }

        /*
         * Validates null pointer
         */
        [TestMethod()]
        public void validateInvalidReferenceTest()
        {
            OptionValidator validator = new OptionValidator();
            Department department = new Department(1, "name", 100);
            Candidate candidate = new Candidate(1, "name", "1231231230", "asd 123");
            Option opt = new Option(candidate, department);
            opt = null;
            try
            {
                validator.validate(opt);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Option is null pointer");
            }
        }

        /*
         * Validates null candidate
         */
        [TestMethod()]
        public void validateInvalidCandidateTest()
        {
            OptionValidator validator = new OptionValidator();
            Department department = new Department(1, "name", 100);
            Candidate candidate = null;
            Option opt = new Option(candidate, department);
            try
            {
                validator.validate(opt);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Candidate is null pointer.\n");
            }
        }

        /*
         * Validates null department
         */
        [TestMethod()]
        public void validateInvalidDepartmentTest()
        {
            OptionValidator validator = new OptionValidator();
            Department department = null;
            Candidate candidate = new Candidate(1, "name", "1231231230", "asd 123");
            Option opt = new Option(candidate, department);
            try
            {
                validator.validate(opt);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Department is null pointer.\n");
            }
        }

        /*
         * Validates null department, candidate
         */
        [TestMethod()]
        public void validateInvalidDepartmentCandidateTest()
        {
            OptionValidator validator = new OptionValidator();
            Department department = null;
            Candidate candidate = null;
            Option opt = new Option(candidate, department);
            try
            {
                validator.validate(opt);
                Assert.IsTrue(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
                Assert.AreEqual(exc.Message, "Candidate is null pointer.\nDepartment is null pointer.\n");
            }
        }
    }
}