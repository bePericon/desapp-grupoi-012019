
export class ErrorResponse {
  public error: {
    error: string,
    message: string,
    path: string,
    status: number,
    timestamp: Date
  };
  public message: string;
}
