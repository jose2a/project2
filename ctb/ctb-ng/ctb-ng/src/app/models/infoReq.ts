import {Employee} from './employee';

export class InfoReq {

    infoReqId: number;
    question: string;
    answer: string;
    provided: boolean;
    employee: Employee;
}