import { Injectable } from '@angular/core';

@Injectable()
export class TitleSenderService {
  private message: string = '學生資料管理系統';

  public getMessage(): string {
    return this.message;
  }
}
