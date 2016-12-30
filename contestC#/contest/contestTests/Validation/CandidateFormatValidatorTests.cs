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
    public class CandidateFormatValidatorTests
    {
        [TestMethod()]
        public void validateFormatTest()
        {
            try
            {
                CandidateFormatValidator.validateFormat("a", "sds", "0732232232", "asdsa");
                Assert.IsFalse(false);
            } catch (ValidatorException)
            {
                Assert.IsTrue(true);
            }
        }

        [TestMethod()]
        public void validateValidFormatTest()
        {
            try
            {
                CandidateFormatValidator.validateFormat("2", "sds", "0732232232", "asdsa");
                Assert.IsFalse(false);
            }
            catch (ValidatorException)
            {
                Assert.IsTrue(true);
            }
        }
    }
}