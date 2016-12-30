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
            Option opt = new Option(1, 1);
            try
            {
                validator.validate(opt);
                Assert.IsTrue(true);
            } catch (ValidatorException)
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
            Option opt = new Option(1, 1);
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
    }
}