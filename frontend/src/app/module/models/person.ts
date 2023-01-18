import {Page} from './page';

export class Person extends Page<Person> {
    content: Person[];
    id: number;
    firstName: string;
    lastName: string;
}
