package uz.uzkassa.controller;

/**
 * @author Dilshodbek Akhmedov, Thu 10:20 PM. 2/23/23
 */

public abstract class AbstractController<S> {
    protected final S service;
    protected final String API = "/api";
    protected final String VERSION = "/v1";
    protected final String PATH = API + VERSION;

    protected AbstractController(S service) {
        this.service = service;
    }
}

