import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable()
export class TransactionService {
  private BASE_URL_FIELDS = 'fields';
  private BASE_URL_VALUES = 'values';

  constructor(private http: HttpClient) {
  }

  getAllFieldsOfCountry(alpha2code: string): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}${this.BASE_URL_FIELDS}/${alpha2code}`);
  }

  saveFieldsValues(model: any, alpha2code: string): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${this.BASE_URL_VALUES}/${alpha2code}`, model);
  }

}
