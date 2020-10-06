import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable()
export class GenerateFormService {
  private BASE_URL_FIELDS = 'fields';
  private BASE_URL_VALUES = 'values';

  constructor(private http: HttpClient) {
  }

  getAllFieldsOfUser(email: string): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}${this.BASE_URL_FIELDS}/${email}`);
  }

  saveFieldsValues(model: any, email: string): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${this.BASE_URL_VALUES}/${email}`, model);
  }

}
