export default class Utils {
    static getDateDiffMillis = (d1: Date, d2: Date) => Math.abs(d2.getTime() - d1.getTime());
}
