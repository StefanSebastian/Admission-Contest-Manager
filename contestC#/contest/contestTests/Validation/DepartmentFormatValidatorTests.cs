using Microsoft.VisualStudio.TestTools.UnitTesting;
using contest.Validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation.Tests
{
    [TestClass()]
    public class DepartmentFormatValidatorTests
    {
        [TestMethod()]
        public void validateFormatTest()
        {
            try
            {
                DepartmentFormatValidator.validateFormat("a", "sds", "12");
                Assert.IsFalse(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void validateValidFormatTest()
        {
            try
            {
                DepartmentFormatValidator.validateFormat("1", "sds", "12");
                Assert.IsFalse(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void validateInvalidPlacesFormatTest()
        {
            try
            {
                DepartmentFormatValidator.validateFormat("1", "sds", "012");
                Assert.IsFalse(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void validateNullPlacesFormatTest()
        {
            try
            {
                DepartmentFormatValidator.validateFormat("1", "sds", "");
                Assert.IsFalse(false);
            }
            catch (ValidatorException exc)
            {
                Assert.IsTrue(true);
            }
        }
    }
}