﻿using contest.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace contest.Validation
{
    public class CandidateFormatValidator
    {
        public static void validateFormat(params string[] fields)
        {
            if (fields.Count() != 4)
            {
                throw new ValidatorException("Invalid number of parameters for candidate.");
            }
            string id = fields[0];
            try
            {
                int idInt = int.Parse(id);
            } catch (FormatException)
            {
                throw new ValidatorException("Invalid format.");
            } catch (ArgumentNullException)
            {
                throw new ValidatorException("Invalid format.");
            }
        }

        public static void validateId(string id)
        {
            try
            {
                int idInt = int.Parse(id);
            } catch (FormatException)
            {
                throw new ValidatorException("Invalid format.");
            }
            catch (ArgumentNullException)
            {
                throw new ValidatorException("Invalid format.");
            }
        }
    }
}
