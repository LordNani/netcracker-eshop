import {Admin} from "../../_model/admin";
import {Injectable} from "@angular/core";

@Injectable()
export class AdminService {
  createAdmin(admin: Admin) {
    console.log("NS: " + admin.NS);
    console.log("job: " + admin.job);
    console.log("status: " + admin.status);
  }
}
