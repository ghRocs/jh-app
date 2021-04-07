import { IRegion } from 'app/shared/model/region.model';
import { Language } from 'app/shared/model/enumerations/language.model';

export interface ICountry {
  id?: number;
  countryName?: string | null;
  language?: Language | null;
  region?: IRegion | null;
}

export const defaultValue: Readonly<ICountry> = {};
