package com.api.apartamentos.controllers;

import org.springframework.web.bind.annotation.*;
import com.api.apartamentos.repositories.IPagosRepository;
import com.api.apartamentos.repositories.IReservaRepository;
import com.api.apartamentos.models.PagosModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final IPagosRepository pagosRepository;
    private final IReservaRepository reservaRepository;

    public ReporteController(IPagosRepository pagosRepository, IReservaRepository reservaRepository) {
        this.pagosRepository = pagosRepository;
        this.reservaRepository = reservaRepository;
    }

    @GetMapping("/ingresos")
    public Map<String, Object> getIngresos() {
        BigDecimal total = pagosRepository.calcularIngresosTotales();
        return Map.of("ingresos_totales", total);
    }

    @GetMapping("/deudores")
    public List<PagosModel> getDeudores() {
        return pagosRepository.buscarPagosDeudores();
    }

    @GetMapping("/ocupados")
    public Map<String, Object> getOcupados() {
        long activas = reservaRepository.contarReservasActivas();
        return Map.of("total_reservas_activas", activas);
    }

}